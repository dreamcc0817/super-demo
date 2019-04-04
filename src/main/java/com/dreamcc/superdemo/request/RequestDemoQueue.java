package com.dreamcc.superdemo.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.request
 * @Description: 请求内存队列
 * @Author: dreamcc
 * @Date: 2019/4/2 17:35
 * @Version: V1.0
 */
public class RequestDemoQueue {

	private List<ArrayBlockingQueue<RequestDemo>> queues = new ArrayList<>();

	/**
	 * 标识位map
	 */
	private Map<Integer,Boolean> flagMap = new ConcurrentHashMap<>();

	/**
	 * 单例有很多种方式去实现
	 * 静态内部类初始化单例是绝对线程安全的一种方式
	 */
	private static class Singleton {

		private static RequestDemoQueue instance;

		static {
			instance = new RequestDemoQueue();
		}

		public static RequestDemoQueue getInstance() {
			return instance;
		}
	}

	/**
	 * jvm的机制去保证多线程并发安全
	 * 内部类的初始化，一定只会发生一次，不管多少个线程并发去初始化
	 *
	 * @return
	 */
	public static RequestDemoQueue getInstance() {
		return Singleton.getInstance();
	}

	/**
	 * 添加一个内存队列
	 *
	 * @param queue
	 */
	public void addQueue(ArrayBlockingQueue<RequestDemo> queue) {
		this.queues.add(queue);
	}

	/**
	 * 获取内存队列的数量
	 *
	 * @return
	 */
	public int queueSize() {
		return queues.size();
	}

	/**
	 * 获取内存队列
	 *
	 * @param index
	 * @return
	 */
	public ArrayBlockingQueue<RequestDemo> getQueue(int index) {
		return queues.get(index);
	}

	public Map<Integer,Boolean> getFlagMap(){
		return flagMap;
	}
}
