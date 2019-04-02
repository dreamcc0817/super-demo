package com.dreamcc.superdemo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.thread
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/2 17:15
 * @Version: V1.0
 */
public class RequestProcessorThreadPool {
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);
}
