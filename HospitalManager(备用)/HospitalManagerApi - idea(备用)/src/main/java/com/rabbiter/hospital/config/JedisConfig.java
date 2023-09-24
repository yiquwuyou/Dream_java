//这是一个Redis的配置类，用于配置连接Redis所需的参数和创建JedisPool实例。
//
//        以下是这个配置类的详细解释：
//
//        JedisConfig - 这是Redis的配置类。
//@PropertySource(value=“classpath:redisConfig.properties”) - 通过@PropertySource注解指定了redisConfig.properties文件的路径，在该文件中配置了Redis的连接参数。
//@Value - 使用@Value注解将redisConfig.properties文件中的参数值注入到对应的成员变量中。
//@Bean(name = “jedisPoolConfig”) - 使用@Bean注解声明了一个名为"jedisPoolConfig"的Bean，用于创建JedisPoolConfig对象，并设置其属性值。
//        getJedisPoolConfig() - 这是方法体，用于创建并返回一个配置好的JedisPoolConfig实例。
//@Bean(name = “jedisPool”) - 使用@Bean注解声明了一个名为"jedisPool"的Bean，用于创建JedisPool对象。
//        getJedisPool() - 这是方法体，用于创建并返回一个JedisPool实例，其中使用了@Qualifier注解指定了jedisPoolConfig参数按名称匹配注入。

package com.rabbiter.hospital.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@PropertySource(value="classpath:redisConfig.properties")
public class JedisConfig {
    @Value(value = "${redis.host}")
    private String host;
    @Value(value = "${redis.port}")
    private int port;
    @Value(value = "${redis.password}")
    private String password;
    @Value(value = "${redis.database}")
    private int database;
    @Value(value = "${redis.maxTotal}")
    private int maxTotal;
    @Value(value = "${redis.maxIdle}")
    private int maxIdle;
    @Value(value = "${redis.minIdle}")
    private int minIdle;
    @Value(value = "${redis.maxWaitMillis}")
    private int maxWaitMillis;
    @Value(value = "${redis.timeout}")
    private int timeout;
    @Value(value = "${redis.testOnBorrow}")
    private boolean testOnBorrow;

    /**
     * spring容器创建一个内存中实例，名字和name值相同
     * @return
     */
    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    /***
     * 获得连接池对象，@Qualifier(value = "jedisPoolConfig")指定参数按名称严格匹配注入
     * @param jedisPoolConfig
     * @return
     */
    @Bean(name = "jedisPool")
    public JedisPool getJedisPool(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {
        return new JedisPool(jedisPoolConfig, host, port, timeout, null, database);
    }

}
