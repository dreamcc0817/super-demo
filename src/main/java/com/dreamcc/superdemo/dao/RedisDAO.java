package com.dreamcc.superdemo.dao;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.dao
 * @Description: Redis工具类
 * @Author: dreamcc
 * @Date: 2019/4/3 14:48
 * @Version: V1.0
 */
public interface RedisDAO {

	/**
	 * set
	 *
	 * @param key
	 * @param value
	 */
	void set(String key, String value);

	/**
	 * get
	 *
	 * @param key
	 * @return
	 */
	String get(String key);

	/**
	 * delete
	 *
	 * @param key
	 */
	void delete(String key);

}
