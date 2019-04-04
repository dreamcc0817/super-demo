package com.dreamcc.superdemo.dao.impl;

import com.dreamcc.superdemo.dao.RedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.dao.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/3 14:51
 * @Version: V1.0
 */
@Repository("redisDAO")
public class RedisDAOImpl implements RedisDAO {

	private JedisCluster jedisCluster;

	@Autowired
	public RedisDAOImpl(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	@Override
	public void set(String key, String value) {
		jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public void delete(String key) {
		jedisCluster.get(key);
	}
}
