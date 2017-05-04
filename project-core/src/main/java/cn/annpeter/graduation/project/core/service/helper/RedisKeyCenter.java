package cn.annpeter.graduation.project.core.service.helper;

import java.util.Date;

/**
 * Created by liupeng on 2017/3/24.
 */
public class RedisKeyCenter {

    /**
     * 用户被看过的总次数countKey
     */
    public static String getUserTotalViewKey() {
        return String.format("connectionInfo:view:total");
    }

    /**
     * 用户被看过的每天次数countKey
     */
    public static String getUserViewKeyByDate(Date date) {
        return String.format("connectionInfo:view:%tF%n", date);
    }

    /**
     * 用户被存储到名片夹的总次数countKey
     */
    public static String getUserTotalStorageKey() {
        return String.format("connectionInfo:storage:total");
    }

    /**
     * 用户被存储到名片夹的按天次数countKey
     */
    public static String getUserStorageKeyByDate(Date date) {
        return String.format("connectionInfo:storage:%tF%n", date);
    }

    /**
     * 用户被加为好友的countKey
     */
    public static String getUserTotalAddKey() {
        return String.format("connectionInfo:add:total");
    }

    public static String getUserAddKeyByDate(Date date) {
        return String.format("connectionInfo:add:%tF%n", date);
    }

    /**
     * 获取用户前几天的人脉信息汇总，最近的是昨天
     */
    public static String getLastDaysUserConnectionInfoKey(int lastDays) {
        return String.format("connectionInfo:last%s", lastDays);
    }
}
