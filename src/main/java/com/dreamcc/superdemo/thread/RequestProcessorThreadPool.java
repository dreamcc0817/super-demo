package com.dreamcc.superdemo.thread;

import com.dreamcc.superdemo.request.RequestDemo;
import com.dreamcc.superdemo.request.RequestDemoQueue;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.*;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.thread
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/2 17:15
 * @Version: V1.0
 */
public class RequestProcessorThreadPool {

	@Value("${thread.corePoolSize}")
	private int corePoolSize;

	@Value("${thread.maximumPoolSize}")
	private int maximumPoolSize;

	/**
	 * 线程池
	 */
	private ExecutorService threadPool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,2000,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

	public RequestProcessorThreadPool(){
		RequestDemoQueue requestDemoQueue = RequestDemoQueue.getInstance();
		for (int i = 0; i < 10; i++) {
			ArrayBlockingQueue<RequestDemo> queue = new ArrayBlockingQueue<>(100);
			requestDemoQueue.addQueue(queue);
			threadPool.submit(new RequestProcessorThread(queue));
		}
	}

	/**
	 * 静态内部类初始化单例
	 */
	public static class Singleton{
		private static RequestProcessorThreadPool instance;

		static{
			instance = new RequestProcessorThreadPool();
		}
		public static RequestProcessorThreadPool getInstance(){
			return instance;
		}
	}

	public static RequestProcessorThreadPool getInstance(){
		return Singleton.getInstance();
	}

	public static void init(){
		getInstance();
	}

}
