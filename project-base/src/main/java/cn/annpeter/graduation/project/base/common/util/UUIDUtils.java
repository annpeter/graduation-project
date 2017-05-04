package cn.annpeter.graduation.project.base.common.util;


import java.util.UUID;

/**
 * MyRandomUtils
 * Created by tianjin.lp on 16/9/4.
 */
public class UUIDUtils {

    public static String getShortUUID() {
        return UUIDtoString(UUID.randomUUID());
    }

    private static String UUIDtoString(UUID u) {
        long mostSigBits = u.getMostSignificantBits();
        long leastSigBits = u.getLeastSignificantBits();
        return (digits(mostSigBits >> 32, 8) + digits(mostSigBits >> 16, 4) + digits(mostSigBits, 4)
                + digits(leastSigBits >> 48, 4) + digits(leastSigBits, 12));
    }

    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toString(hi | (val & (hi - 1)), 36).substring(1);
    }
}
