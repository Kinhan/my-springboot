//package com.ljh.myspringboot.cache;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import javax.annotation.Resource;
//import java.lang.reflect.Method;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @Description: java类作用描述
// * @Author: linjinhan
// * @CreateDate: 2018/10/1 10:03
// */
////@Configuration
////@EnableCaching
//public class RedisConfig extends CachingConfigurerSupport {
//
////    @Resource
//    private LettuceConnectionFactory lettuceConnectionFactory;
//
//    @Override
////    @Bean
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append(method.getName());
//                for (Object obj : params) {
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }
//
//
////    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//
////        //初始化一个RedisCacheWriter
////        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
////        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
////        //ClassLoader loader = this.getClass().getClassLoader();
////        //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
////        //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
////        //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
////        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
////        //默认过期时间，秒
////        defaultCacheConfig.entryTtl(Duration.ofSeconds(60));
////        RedisCacheManager rcm = new RedisCacheManager(redisCacheWriter,defaultCacheConfig);
////        //设置缓存过期时间
////        return rcm;
//
//        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(lettuceConnectionFactory);
//        @SuppressWarnings("serial") Set<String> cacheNames = new HashSet<String>() {
//            {
//                add("codeNameCache");
//            }
//        };
//        builder.initialCacheNames(cacheNames);
//        return builder.build();
//
//    }
//
////    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
////        StringRedisTemplate template = new StringRedisTemplate(factory);
////        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
////        ObjectMapper om = new ObjectMapper();
////        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
////        jackson2JsonRedisSerializer.setObjectMapper(om);
////        template.setValueSerializer(jackson2JsonRedisSerializer);
////        template.afterPropertiesSet();
////        return template;
//        // 设置序列化
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
//        om.enableDefaultTyping(DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        // 配置redisTemplate
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
//        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer);
//        // key序列化
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        // value序列化
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        // Hash key序列化
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        // Hash value序列化
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//}
