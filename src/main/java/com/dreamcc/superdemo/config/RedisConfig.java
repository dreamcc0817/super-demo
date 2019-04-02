package com.dreamcc.superdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.config
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/2 16:51
 * @Version: V1.0
 */
@Configuration
public class RedisConfig {

	/**
	 * 配置redis地址
	 *
	 * @return
	 */
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> jedisClusterNodes = new HashSet<>();
		jedisClusterNodes.add(new HostAndPort("", 7003));
		jedisClusterNodes.add(new HostAndPort("", 7003));
		jedisClusterNodes.add(new HostAndPort("", 7003));
		jedisClusterNodes.add(new HostAndPort("", 7003));
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
		return jedisCluster;
	}
}
