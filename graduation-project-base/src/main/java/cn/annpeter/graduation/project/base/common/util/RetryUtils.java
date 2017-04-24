package cn.annpeter.graduation.project.base.common.util;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.exception.TryFailureException;
import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 重试工具类
 * Created by tianjin.lp on 16/12/27.
 */
public class RetryUtils {
    private static final Logger logger = LoggerFactory.getLogger(RetryUtils.class);
    private static final long MAX_SLEEP_MILLISECOND = 60000;

    public static <T> T retryOrDiscard(Callable<T> callable, int retryTimes,
                                       long sleepTime, boolean exp, String title) {
        return retryOrDiscard(callable, retryTimes, sleepTime, TimeUnit.MILLISECONDS, exp, title);
    }

    /**
     * 重试不成功，则直接忽略
     */
    public static <T> T retryOrDiscard(Callable<T> callable, int retryTimes,
                                       long sleepTime, TimeUnit unit, boolean exp, String title) {
        try {
            return retryOrThrow(callable, retryTimes, sleepTime, unit, exp, title);
        } catch (TryFailureException e) {
            logger.warn("{} retryOrDiscard says {}, but will be discard.", title, e.getMessage(), e);
            return null;
        }
    }

    public static <T> T retryOrThrowCommonException(Callable<T> callable, long sleepTime, String title) {
        return retryOrThrowCommonException(callable, 3, sleepTime, TimeUnit.MILLISECONDS, true, title);
    }

    public static <T> T retryOrThrowCommonException(Callable<T> callable, long sleepTime, boolean exp,
                                                    String title) {
        return retryOrThrowCommonException(callable, 3, sleepTime, TimeUnit.MILLISECONDS, exp, title);
    }

    public static <T> T retryOrThrowCommonException(Callable<T> callable, int retryTimes,
                                                    long sleepTime, boolean exp, String title) {
        return retryOrThrowCommonException(callable, retryTimes, sleepTime, TimeUnit.MILLISECONDS, exp, title);
    }

    /**
     * 重试不成功，则抛出CommonException
     */
    public static <T> T retryOrThrowCommonException(Callable<T> callable, int retryTimes,
                                                    long sleepTime, TimeUnit unit, boolean exp, String title) {
        try {
            return retryOrThrow(callable, retryTimes, sleepTime, unit, exp, title);
        } catch (TryFailureException e) {
            logger.warn("{} retryOrThrowCommonException says {}, will throw commonException.", title, e.getMessage(), e);
            throw new CommonException(ResultCodeEnum.SERVER_POWER_LESS, e);
        }
    }

    public static <T> T retryOrThrow(Callable<T> callable, long sleepTime, String title) throws TryFailureException {
        return retryOrThrow(callable, 3, sleepTime, TimeUnit.MILLISECONDS, true, title);
    }

    public static <T> T retryOrThrow(Callable<T> callable, long sleepTime, boolean exp, String title) throws TryFailureException {
        return retryOrThrow(callable, 3, sleepTime, TimeUnit.MILLISECONDS, exp, title);
    }

    public static <T> T retryOrThrow(Callable<T> callable, int retryTimes,
                                     long sleepTime, boolean exp, String title) throws TryFailureException {
        return retryOrThrow(callable, retryTimes, sleepTime, TimeUnit.MILLISECONDS, exp, title);
    }

    /**
     * 重试次数工具方法.
     *
     * @param callable   实际逻辑
     * @param retryTimes 最大重试次数（>1）
     * @param sleepTime  运行失败后休眠对应时间再重试
     * @param exp        休眠时间是否指数递增
     * @param <T>        返回值类型
     */
    public static <T> T retryOrThrow(Callable<T> callable, int retryTimes,
                                     long sleepTime, TimeUnit unit, boolean exp, String title) throws TryFailureException {

        Assert.notNull(callable, "系统编程错误, 入参callable不能为空!");
        Assert.isTrue(retryTimes >= 1, "系统编程错误, retryTimes不能小于1!");
        Assert.isTrue(retryTimes >= 1, "系统编程错误, sleepTimeInMilliSecond不能小于0!");

        TryFailureException saveException = null;
        for (int index = 1; index <= retryTimes; index++) {
            try {
                return callable.call();
            } catch (Exception e) {
                logger.warn("{} on RetryUtils current retry {} times, last fail message is {}.", title, index, e.getMessage());
                saveException = new TryFailureException(e);
                if (index < retryTimes) {
                    long timeToSleep = exp ? sleepTime * (long) Math.pow(2, index) : sleepTime;

                    if (timeToSleep >= MAX_SLEEP_MILLISECOND) {
                        timeToSleep = MAX_SLEEP_MILLISECOND;
                    }

                    try {
                        unit.sleep(timeToSleep);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }

        if (saveException == null) {
            // 理论上是不会到这里的
            throw new TryFailureException("重试方法多次失败，终止重试 .");
        }
        throw saveException;
    }
}
