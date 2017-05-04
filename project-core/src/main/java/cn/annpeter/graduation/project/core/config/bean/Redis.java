package cn.annpeter.graduation.project.core.config.bean;

/**
 * Created on 2017/04/06
 *
 * @author annpeter.it@gmail.com
 */
public class Redis {
    public StandAlone standAlone;
    public Pool pool;

    public Integer dbIndex;
    public Boolean cacheOn;

    public static final class Pool {
        public Integer maxIdle;
        public Boolean testOnBorrow;
        public Boolean testOnReturn;
    }

    public static final class StandAlone {
        public String redisHostName;
        public Integer redisPort;
        public String password;
    }
}