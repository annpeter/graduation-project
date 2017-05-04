package cn.annpeter.graduation.project.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.redis;

/**
 * Created on 2017/03/19
 *
 * @author annpeter.it@gmail.com
 */
@Configuration
public class RedisConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        logger.info("==== jedisConnectionFactory init ====");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redis.pool.maxIdle);
        poolConfig.setTestOnBorrow(redis.pool.testOnBorrow);
        poolConfig.setTestOnReturn(redis.pool.testOnReturn);

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setPassword(redis.standAlone.password);
        connectionFactory.setHostName(redis.standAlone.redisHostName);
        connectionFactory.setPort(redis.standAlone.redisPort);
        connectionFactory.setDatabase(redis.dbIndex);

        return connectionFactory;
    }

    @Bean(value = {"springSessionDefaultRedisSerializer", "genericJackson2JsonRedisSerializer"})
    public static GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean(value = {"sessionRedisTemplate", "redisTemplate"})
    public static RedisTemplate<Object, Object> redisTemplate(GenericJackson2JsonRedisSerializer jsonRedisSerializer,
                                                              JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        template.setDefaultSerializer(jsonRedisSerializer);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jsonRedisSerializer);
        return template;
    }
}
