package com.dreamcc.superdemo.service.impl;

import com.dreamcc.superdemo.request.RequestDemo;
import com.dreamcc.superdemo.request.RequestDemoQueue;
import com.dreamcc.superdemo.service.RequestAsyncProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.service.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/3 16:39
 * @Version: V1.0
 */
@Slf4j
@Service("requestAsyncProcessService")
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {
	@Override
	public void process(RequestDemo request) {
		try {
			//做请求的路由，根据每个请求的商品id，路由到对应内存队列中去
			ArrayBlockingQueue<RequestDemo> queue = getRoutingQueue(request.getProductId());
			//将请求放入对应的队列中，完成路由操作
			queue.put(request);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取路由到内存队列
	 *
	 * @param productId 商品id
	 * @return 内存队列
	 */
	private ArrayBlockingQueue<RequestDemo> getRoutingQueue(Integer productId) {
		RequestDemoQueue requestDemoQueue = RequestDemoQueue.getInstance();
		String key = String.valueOf(productId);
		int h;
		int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
		int index = (requestDemoQueue.queueSize() - 1) & hash;
		log.info("================日志================");
		log.info("路由内存队列，商品id：{}，队列索引：{}",productId,index);
		return requestDemoQueue.getQueue(index);
	}
}
