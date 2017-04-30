package cn.annpeter.graduation.project.core.service.helper;

import java.util.Date;

/**
 * Created by liupeng on 2017/3/24.
 */
public class RedisKeyCenter {

    /**
     * 用户被看过的总次数countKey
     */
    public static String getUserTotalViewKey(Integer userId) {
        return String.format("user:%s:connectionInfo:view:total", userId);
    }

    /**
     * 用户被看过的每天次数countKey
     */
    public static String getUserViewKeyByDate(Integer userId, Date date) {
        return String.format("user:%s:connectionInfo:view:%tF%n", userId, date);
    }

    /**
     * 企业被看过的总数countKey
     */
    public static String getCorpTotalViewKey(Integer corpId) {
        return String.format("corp:%s:connectionInfo:view:total", corpId);
    }

    /**
     * 企业被看过的按天次数countKey
     */
    public static String getCorpViewKeyByDate(Integer corpId, Date date) {
        return String.format("corp:%s:connectionInfo:view:%tF%n", corpId, date);
    }

    /**
     * 用户被存储到名片夹的总次数countKey
     */
    public static String getUserTotalStorageKey(Integer corpId) {
        return String.format("user:%s:connectionInfo:storage:total", corpId);
    }

    /**
     * 用户被存储到名片夹的按天次数countKey
     */
    public static String getUserStorageKeyByDate(Integer corpId, Date date) {
        return String.format("user:%s:connectionInfo:storage:%tF%n", corpId, date);
    }

    /**
     * 企业被存储到名片夹的countKey
     */
    public static String getCorpTotalStorageKey(Integer corpId) {
        return String.format("corp:%s:connectionInfo:storage:total", corpId);
    }

    /**
     * 企业被存储到名片夹的当天数量countKey
     */
    public static String getCorpTodayStorageKey(Integer corpId) {
        return String.format("corp:%s:connectionInfo:storage:%tF%n", corpId, new Date());
    }

    /**
     * 用户被加为好友的countKey
     */
    public static String getUserTotalAddKey(Integer corpId) {
        return String.format("user:%s:connectionInfo:add:total", corpId);
    }

    public static String getUserAddKeyByDate(Integer corpId, Date date) {
        return String.format("user:%s:connectionInfo:add:%tF%n", corpId, date);
    }

    /**
     * 企业被加为好友的countKey
     */
    public static String getCorpTotalAddKey(Integer corpId) {
        return String.format("corp:%s:connectionInfo:storage:total", corpId);
    }

    public static String getCorpAddKeyByDate(Integer corpId, Date date) {
        return String.format("corp:%s:connectionInfo:storage:%tF%n", corpId, date);
    }

    /**
     * 获取用户前几天的人脉信息汇总，最近的是昨天
     */
    public static String getLastDaysUserConnectionInfoKey(Integer userId, int lastDays) {
        return String.format("user:%s:connectionInfo:last%s", userId, lastDays);
    }


    /**
     * 获取企业工商信息
     */
    public static String getBusinessInformationKey(Integer corpId) {
        return String.format("corp:%s:businessInformation", corpId);
    }

    /**
     * 获取企业三方信息的时间戳
     */
    public static String getTripartiteModifiedKey(Integer corpId) {
        return String.format("corp:%s:tripartiteModified", corpId);
    }
}
