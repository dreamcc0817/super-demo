package com.dreamcc.superdemo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.thread
 * @Description: 执行请求的工作线程
 * @Author: dreamcc
 * @Date: 2019/4/2 17:12
 * @Version: V1.0
 */
public class RequestProcessorThread implements Callable<Boolean> {

	/**
	 * 自己监控的内存队列
	 */
	private ArrayBlockingQueue queue;

	public RequestProcessorThread(ArrayBlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public Boolean call() throws Exception {
		while (true) {
			break;
		}
		return true;
	}
}
