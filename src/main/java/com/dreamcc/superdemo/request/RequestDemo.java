package com.dreamcc.superdemo.request;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.request
 * @Description: 请求接口
 * @Author: dreamcc
 * @Date: 2019/4/2 17:35
 * @Version: V1.0
 */
public interface RequestDemo {
	/**
	 * 相对应操作
	 */
	void process();

	/**
	 * 获取商品ID
	 *
	 * @return
	 */
	Integer getProductId();

	/**
	 * 是否强制刷新缓存
	 *
	 * @return 是或者否
	 */
	boolean isForceRefresh();
}
