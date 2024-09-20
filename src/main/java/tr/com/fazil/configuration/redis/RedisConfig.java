package tr.com.fazil.configuration.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
@EnableCaching
@RequiredArgsConstructor
@EnableConfigurationProperties(RedisConfigurationProperties.class)
public class RedisConfig {

    private final RedisConfigurationProperties redisConfigurationProperties;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMinIdle(redisConfigurationProperties.getMinIdle());
        jedisPoolConfig.setMaxIdle(redisConfigurationProperties.getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisConfigurationProperties.getMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(redisConfigurationProperties.getMaxWaitMillis());

        return jedisPoolConfig;
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisConfigurationProperties.getNodes().get(0));
        redisStandaloneConfiguration.setPort(redisConfigurationProperties.getPort());
        return redisStandaloneConfiguration;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(redisStandaloneConfiguration(), jedisClientConfiguration());
    }


    @Bean
    public JedisClientConfiguration jedisClientConfiguration() {
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        builder.connectTimeout(Duration.ofMillis(redisConfigurationProperties.getConnectTimeout()));
        builder.readTimeout(Duration.ofMillis(redisConfigurationProperties.getReadTimeout()));
        return builder.usePooling()
                .poolConfig(jedisPoolConfig())
                .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

}
