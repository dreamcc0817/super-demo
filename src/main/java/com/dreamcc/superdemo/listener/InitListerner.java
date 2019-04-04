package com.dreamcc.superdemo.listener;

import com.dreamcc.superdemo.thread.RequestProcessorThreadPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.listener
 * @Description: 系统初始化监听器
 * @Author: dreamcc
 * @Date: 2019/4/2 17:07
 * @Version: V1.0
 */
public class InitListerner implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		RequestProcessorThreadPool.init();
	}
}
