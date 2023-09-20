package com.rabbiter.hospital.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

// 配置 Redis 连接池相关的参数
@Configuration
// 这个注解指定了配置文件的位置 即classpath:redisConfig.properties  这个配置文件包含了 Redis 相关的属性值
// classpath 是一个用于指定类路径的标识符
@PropertySource(value="classpath:redisConfig.properties")
public class JedisConfig {
    // 使用 @Value 注解注入属性值：通过 @Value 注解，将配置文件中的属性值注入到对应的字段中。
    // 例如，${redis.host} 将会注入到 host 字段中

    // 这些字段的值将从配置文件中读取，
    // 配置文件通常是 application.properties 或 application.yml（或其他命名约定的配置文件）。
    // 这允许你在不修改代码的情况下轻松更改 Redis 连接池的配置，以适应不同的环境和需求
    @Value(value = "${redis.host}")
    private String host;         // host：Redis 服务器的主机名或 IP 地址
    @Value(value = "${redis.port}")
    private int port;            // port：Redis 服务器的端口号
    @Value(value = "${redis.password}")
    private String password;     // password：连接 Redis 服务器所需的密码，如果没有密码则为空字符串
    @Value(value = "${redis.database}")
    private int database;        // database：要连接的 Redis 数据库的索引
    @Value(value = "${redis.maxTotal}")
    private int maxTotal;        // maxTotal：连接池中的最大连接数
    // 连接池是一种管理数据库连接的机制，它允许应用程序在需要时从连接池中获取数据库连接，而不是每次都创建新的连接，从而提高了性能和资源利用率
    @Value(value = "${redis.maxIdle}")
    private int maxIdle;         // maxIdle：连接池中的最大空闲连接数
    @Value(value = "${redis.minIdle}")
    private int minIdle;         // minIdle：连接池中的最小空闲连接数
    @Value(value = "${redis.maxWaitMillis}")
    private int maxWaitMillis;   // maxWaitMillis：当连接池耗尽且需要等待连接时，等待的最大毫秒数
    @Value(value = "${redis.timeout}")
    private int timeout;         // timeout：连接 Redis 服务器的超时时间，以毫秒为单位
    @Value(value = "${redis.testOnBorrow}")
    private boolean testOnBorrow;     // testOnBorrow：是否在从连接池中获取连接时测试连接的可用性，通常设置为 true

    /**
     * spring容器创建一个内存中实例，名字和name值相同
     * @return
     */
    // 这段代码是一个Spring Bean 的定义方法，它创建了一个名为 "jedisPoolConfig" 的对象，并配置了 Jedis 连接池的一些属性
    // 与上面那部分联系起来
    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 拿下面这行代码举例：
        // setMaxTotal(maxTotal) - 设置连接池中的最大连接数，这是连接池可以同时管理的最大连接数量，也就是前面提到的 maxTotal
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

    // 这段代码定义了一个名为 "jedisPool" 的Spring Bean，它用于创建一个 Jedis 连接池对象
    // 上面那个是配置！ 这个是创建！！！  理清楚
    // 这个方法的目的是创建一个 Jedis 连接池对象，以便在应用程序中能够方便地获取和管理与Redis服务器的连接。
    // 连接池的配置信息是通过 jedisPoolConfig 参数提供的，这样可以轻松地调整连接池的配置以满足应用程序的需求。
    // 连接池的使用可以有效地管理和复用连接，提高了与Redis的交互效率
    @Bean(name = "jedisPool")   // 这个注解告诉Spring容器要创建一个名为 "jedisPool" 的Bean，并将该Bean纳入Spring管理
    // 它接收了一个参数jedisPoolConfig，该参数是一个名为 “jedisPoolConfig” 的引用，与前面配置信息的方法联系起来
    // @Qualifier 注解用于指定要注入的Bean的名称，确保使用正确的连接池配置
    public JedisPool getJedisPool(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {
        // 创建了一个 JedisPool 对象，它使用了上面定义的连接池配置 (jedisPoolConfig)，并传递了一些连接参数
        return new JedisPool(jedisPoolConfig, host, port, timeout, null, database);
    }

}
