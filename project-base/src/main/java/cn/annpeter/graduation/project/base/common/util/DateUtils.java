package cn.annpeter.graduation.project.base.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by liupeng on 2017/3/24.
 */
public class DateUtils {
    public static Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    public static int getDaysBetweenTwoDate(Date beforeDate, Date afterDate) {
        return (int) (afterDate.getTime() - beforeDate.getTime()) / (24 * 60 * 60 * 1000);
    }

}
