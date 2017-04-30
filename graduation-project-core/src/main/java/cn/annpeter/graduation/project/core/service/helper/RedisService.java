package cn.annpeter.graduation.project.core.service.helper;

import cn.annpeter.graduation.project.base.common.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liupeng on 2017/3/24.
 */
@Service
public class RedisService {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * increment一批key
     */
    public void atomicIncrementMultiKeys(List<String> keyList, long timeout, TimeUnit timeUnit) {
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public String execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                for (Object key : keyList) {
                    operations.opsForValue().increment(key, 1);
                    operations.expire(key, timeout, timeUnit);
                }
                operations.exec();
                return null;
            }
        });
    }

    public void atomicIncrementMultiKeys(List<String> keyList) {
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public String execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                for (Object key : keyList) {
                    operations.opsForValue().increment(key, 1);
                }
                operations.exec();
                return null;
            }
        });
    }

    public BoundListOperations<String, Object> listOperations(String key) {
        return (BoundListOperations) redisTemplate.boundListOps(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void atomicSetMultiMap(Map<String, Object> map) {
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public String execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                for (String key : map.keySet()) {
                    redisTemplate.opsForValue().set(key, map.get(key));
                }
                operations.exec();
                return null;
            }
        });
    }

    public <T> T getObject(String key, TypeReference type) {
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj == null) {
            return null;
        }
        return JsonUtils.string2Object(String.valueOf(obj), type);
    }
}
