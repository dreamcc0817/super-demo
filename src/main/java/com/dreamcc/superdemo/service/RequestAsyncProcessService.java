package com.dreamcc.superdemo.service;

import com.dreamcc.superdemo.request.RequestDemo;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.service
 * @Description: 请求异步执行的service
 * @Author: dreamcc
 * @Date: 2019/4/3 16:37
 * @Version: V1.0
 */
public interface RequestAsyncProcessService {
	void process(RequestDemo request);
}
