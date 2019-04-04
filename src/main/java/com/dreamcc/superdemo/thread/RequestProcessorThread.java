package com.dreamcc.superdemo.thread;

import com.dreamcc.superdemo.request.ProductInventoryCacheRefreshRequest;
import com.dreamcc.superdemo.request.ProductInventoryDBUpdateRequest;
import com.dreamcc.superdemo.request.RequestDemo;
import com.dreamcc.superdemo.request.RequestDemoQueue;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
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
@Slf4j
public class RequestProcessorThread implements Callable<Boolean> {

	/**
	 * 自己监控的内存队列
	 */
	private ArrayBlockingQueue<RequestDemo> queue;

	public RequestProcessorThread(ArrayBlockingQueue<RequestDemo> queue) {
		this.queue = queue;
	}

	@Override
	public Boolean call() throws Exception {
		while (true) {
			//ArrayBlockingQueue
			//Blocking就是说明，如果队列满了，或者是空的，那么都会在执行操作的时候阻塞住
			RequestDemo requestDemo = queue.take();
			boolean forceReresh = requestDemo.isForceRefresh();

			//先做读请求的去重
			if (!forceReresh) {
				RequestDemoQueue requestDemoQueue = RequestDemoQueue.getInstance();
				Map<Integer, Boolean> flagMap = requestDemoQueue.getFlagMap();

				if (requestDemo instanceof ProductInventoryDBUpdateRequest) {
					//如果是一个更新数据库的请求，那么就将那个productId对应的标识设置为true
					flagMap.put(requestDemo.getProductId(), true);
				} else if (requestDemo instanceof ProductInventoryCacheRefreshRequest) {
					Boolean flag = flagMap.get(requestDemo.getProductId());

					if (flag == null) {
						flagMap.put(requestDemo.getProductId(), false);
					}

					//如果缓存刷新的请求，那么就判断，如果表示不为空，而且是true，就说明之前有一个这个商品的数据库更新请求
					if (flag != null && flag) {
						flagMap.put(requestDemo.getProductId(), false);
					}
					// 如果是缓存刷新的请求，而且发现标识不为空，但是标识是false
					// 说明前面已经有一个数据库更新请求+一个缓存刷新请求了，大家想一想
					if (flag != null && !flag) {
						// 对于这种读请求，直接就过滤掉，不要放到后面的内存队列里面去了
						return true;
					}
				}
			}
			log.info("==============日志================");
			log.info("工作线程处理请求，商品id：{}",requestDemo.getProductId());
			requestDemo.process();
		}
	}
}
