package cn.annpeter.graduation.project.core.service.helper;

import javaslang.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


/**
 * Created on 2017/03/19
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class CacheTemplateService {
    private static final Logger logger = LoggerFactory.getLogger(CacheTemplateService.class);

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    private boolean cacheOn = true;

    /**
     * 避免缓存穿透的模板
     *
     * @param key      缓存的key
     * @param timeout  缓存的失效时间
     * @param unit     失效时间单位
     * @param loadBack 如果缓存失效, 怎么获取
     */
    public Object findCache(String key, long timeout, TimeUnit unit, Callable<Object> loadBack) {
        if (cacheOn) {
            Object objFirstLoad = redisTemplate.opsForValue().get(key);
            if (objFirstLoad != null) {
                logger.info("load cache ======== {}.", key);
                return objFirstLoad;
            } else {
                synchronized (key) {
                    Object objSecondLoad = redisTemplate.opsForValue().get(key);
                    if (objSecondLoad != null) {
                        logger.info("load cache ======== {}.", key);
                        return objSecondLoad;
                    }

                    Object result = doLoadBack(loadBack);

                    if (result != null) {
                        redisTemplate.opsForValue().set(key, result, timeout, unit);
                    }
                    return result;
                }
            }
        } else {
            return doLoadBack(loadBack);
        }
    }

    public Object findCache(String key, long timeout, Callable<Object> loadBack) {
        return findCache(key, timeout, TimeUnit.SECONDS, loadBack);
    }

    /**
     * 第一次去后端查询, 第二次使用默认值 (对那些只做一次的事情, 用作标志位很有用)
     */
    public Object findCache(String key, Callable<Object> loadBack, Object secondTimeObj) {
        if (cacheOn) {
            Object objFirstLoad = redisTemplate.opsForValue().get(key);
            if (objFirstLoad != null) {
                logger.info("load cache ======== {}.", key);
                return objFirstLoad;
            } else {
                synchronized (key) {
                    Object objSecondLoad = redisTemplate.opsForValue().get(key);
                    if (objSecondLoad != null) {
                        logger.info("load cache ======== {}.", key);
                        return objSecondLoad;
                    }

                    Object result = doLoadBack(loadBack);

                    if (secondTimeObj != null) {
                        redisTemplate.opsForValue().set(key, secondTimeObj);
                    }
                    return result;
                }
            }
        } else {
            return doLoadBack(loadBack);
        }
    }

    private Object doLoadBack(Callable<Object> loadBack) {
        return Try.of(loadBack::call).getOrElseThrow((e) -> {throw new RuntimeException(e);});
    }
}
