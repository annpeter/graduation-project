package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.common.util.DateUtils;
import cn.annpeter.graduation.project.base.common.util.JsonUtils;
import cn.annpeter.graduation.project.core.service.helper.ApplicationExecutorHolder;
import cn.annpeter.graduation.project.core.service.helper.RedisKeyCenter;
import cn.annpeter.graduation.project.core.service.helper.RedisService;
import cn.annpeter.graduation.project.core.service.helper.UserConnectionInfoVO;
import cn.annpeter.graduation.project.core.service.helper.UserConnectionInfoVO.ConnectionInfoItem;
import com.fasterxml.jackson.core.type.TypeReference;
import javaslang.control.Try;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 基于redis的统计相关服务
 * Created by liupeng on 2017/3/23.
 */
@Service
public class CountingService {

    @Resource
    private RedisService redisService;

    /**
     * 用户被看过的count + 1
     */
    public void incrementUserViewCount(Integer userId, Integer corpId) {
        redisService.atomicIncrementMultiKeys(Arrays.asList(
                RedisKeyCenter.getUserViewKeyByDate(userId, new Date()),
                RedisKeyCenter.getCorpViewKeyByDate(corpId, new Date())
        ), 45, TimeUnit.DAYS);
        redisService.atomicIncrementMultiKeys(Arrays.asList(
                RedisKeyCenter.getUserTotalViewKey(userId),
                RedisKeyCenter.getCorpTotalViewKey(corpId)
        ));
    }

    /**
     * 用户被保存过的count + 1
     */
    public void incrementUserStorageCount(Integer userId, Integer corpId) {

        redisService.atomicIncrementMultiKeys(Arrays.asList(
                RedisKeyCenter.getUserStorageKeyByDate(userId, new Date())
        ), 45, TimeUnit.DAYS);
        redisService.atomicIncrementMultiKeys(Arrays.asList(
                RedisKeyCenter.getUserTotalStorageKey(userId),
                RedisKeyCenter.getCorpTodayStorageKey(corpId),
                RedisKeyCenter.getCorpTotalStorageKey(corpId)
        ));
    }

    /**
     * 用户被添加过的count + 1
     */
    public void incrementUserAddCount(Integer userId, Integer corpId) {
        redisService.atomicIncrementMultiKeys(Arrays.asList(
                RedisKeyCenter.getUserAddKeyByDate(userId, new Date()),
                RedisKeyCenter.getCorpAddKeyByDate(corpId, new Date())
        ), 45, TimeUnit.DAYS);
        redisService.atomicIncrementMultiKeys(Arrays.asList(
                RedisKeyCenter.getUserTotalAddKey(userId),
                RedisKeyCenter.getCorpTotalAddKey(corpId)
        ));
    }

    /**
     * 获取个人的总体人脉信息，包括如下信息
     * 1、个人当天的数据统计
     * 2、个人总体数据统计
     * 3、个人最近30天的数据统计
     */
    public UserConnectionInfoVO getUserConnectionInfoVO(Integer userId) {
        UserConnectionInfoVO connectionInfoVO = new UserConnectionInfoVO();

        FutureTask<List<ConnectionInfoItem>> itemListFuture = new FutureTask<>(() -> getLastDaysUserConnectionInfo(userId, 29));
        FutureTask<ConnectionInfoItem> todayItemFuture = new FutureTask<>(() -> getUserTodayConnectionInfo(userId));
        FutureTask<ConnectionInfoItem> totalItemFuture = new FutureTask<>(() -> getUserTotalConnectionInfo(userId));

        ApplicationExecutorHolder.execute("Redis获取UserConnectionInfo-29天数据, UserId=" + userId, itemListFuture);
        ApplicationExecutorHolder.execute("Redis获取UserConnectionInfo-今天数据, UserId=" + userId, todayItemFuture);
        ApplicationExecutorHolder.execute("Redis获取UserConnectionInfo-总数据, UserId=" + userId, totalItemFuture);

        Try.run(() -> {
            ConnectionInfoItem todayItem = todayItemFuture.get();
            connectionInfoVO.setToday(todayItem);
            connectionInfoVO.setTotal(totalItemFuture.get());
            List<ConnectionInfoItem> itemList = itemListFuture.get();
            itemList.add(todayItem);
            connectionInfoVO.setDays(itemList);
        }).getOrElseThrow((e) -> {
            throw new RuntimeException("获取UserConnectionInfoVO失败, UserId=" + userId, e);
        });
        return connectionInfoVO;
    }

    /**
     * 根据日期获取人脉信息当天汇总
     */
    private ConnectionInfoItem getUserConnectionInfoByDate(Integer userId, Date date) {
        ConnectionInfoItem connectionInfoItem = new ConnectionInfoItem();
        connectionInfoItem.setView(getCount(RedisKeyCenter.getUserViewKeyByDate(userId, date)));
        connectionInfoItem.setAdd(getCount(RedisKeyCenter.getUserAddKeyByDate(userId, date)));
        connectionInfoItem.setStorage(getCount(RedisKeyCenter.getUserStorageKeyByDate(userId, date)));
        connectionInfoItem.setDate(date);
        return connectionInfoItem;
    }

    /**
     * 获取当天的人脉信息
     */
    private ConnectionInfoItem getUserTodayConnectionInfo(Integer userId) {
        return getUserConnectionInfoByDate(userId, new Date());
    }

    /**
     * 获取人脉信息全部汇总
     */
    private ConnectionInfoItem getUserTotalConnectionInfo(Integer userId) {
        ConnectionInfoItem connectionInfoItem = new ConnectionInfoItem();
        connectionInfoItem.setView(getCount(RedisKeyCenter.getUserTotalViewKey(userId)));
        connectionInfoItem.setAdd(getCount(RedisKeyCenter.getUserTotalAddKey(userId)));
        connectionInfoItem.setStorage(getCount(RedisKeyCenter.getUserTotalStorageKey(userId)));
        connectionInfoItem.setDate(new Date());
        return connectionInfoItem;
    }

    /**
     * 获取个人最近几天的人脉信息统计汇总，最新的是昨天
     */
    public List<ConnectionInfoItem> getLastDaysUserConnectionInfo(Integer userId, int lastDays) {
        // 先尝试从redis获取最近几天的所有信息
        BoundListOperations<String, Object> lastDayInfoOperations = redisService.listOperations(RedisKeyCenter.getLastDaysUserConnectionInfoKey(userId, lastDays));

        // 如果发现redis没有则重新建立
        Long size = lastDayInfoOperations.size();
        if (size == 0L) {
            return updateUserHistoryConnections(userId, lastDays, lastDays, lastDayInfoOperations);
        }

        // 找到队列中最大的那一天
        ConnectionInfoItem lastItem = JsonUtils.string2Object((String) lastDayInfoOperations.index(size - 1), ConnectionInfoItem.class);

        // 如果最新的就是昨天的，则直接返回返回
        if (org.apache.commons.lang3.time.DateUtils.isSameDay(lastItem.getDate(), DateUtils.getYesterday())) {
            return lastDayInfoOperations.range(0, size).parallelStream()
                    .map((item) -> JsonUtils.string2Object((String) item, ConnectionInfoItem.class))
                    .collect(Collectors.toList());
        } else {
            // 计算出来中间需要补全几天的信息，直接进入重建
            Date lastDate = lastItem.getDate();

            int daysNumToFix = DateUtils.getDaysBetweenTwoDate(lastDate, DateUtils.getYesterday());
            if (daysNumToFix >= lastDays || daysNumToFix <= 0) {
                // 此种情况属于异常情况，直接重建
                daysNumToFix = lastDays;
                redisService.delete(RedisKeyCenter.getLastDaysUserConnectionInfoKey(userId, lastDays));
            }
            return updateUserHistoryConnections(userId, daysNumToFix, lastDays, lastDayInfoOperations);
        }
    }


    /**
     * 补全用户所有的联系信息天数，不够则补全，另外更新成最新的，即：昨天的数据是队尾
     */
    private List<ConnectionInfoItem> updateUserHistoryConnections(Integer userId, int fixDayNum, int allNum, BoundListOperations<String, Object> lastDayInfoOperations) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, (0 - fixDayNum));

        List<ConnectionInfoItem> rightPushItem = lastDayInfoOperations.range(0, lastDayInfoOperations.size()).parallelStream()
                .map((item) -> JsonUtils.string2Object((String) item, ConnectionInfoItem.class))
                .collect(Collectors.toList());

        for (int i = 1; i <= fixDayNum; i++) {
            // 从昨天开始计算，补全所有的联系信息
            ConnectionInfoItem connectionInfoItem = getUserConnectionInfoByDate(userId, calendar.getTime());
            rightPushItem.add(connectionInfoItem);
            calendar.add(Calendar.DAY_OF_YEAR, 1);

            ApplicationExecutorHolder.execute("Redis补全userId=" + userId + "的人脉信息数据", () -> {
                lastDayInfoOperations.rightPush(JsonUtils.object2String(connectionInfoItem));
                if (lastDayInfoOperations.size() > allNum) {
                    lastDayInfoOperations.leftPop();
                }
            });
        }

        return rightPushItem;
    }

    private Integer getCount(String key) {
        Integer count = redisService.getObject(key, new TypeReference<Integer>() {
        });
        return count == null ? 0 : count;
    }
}
