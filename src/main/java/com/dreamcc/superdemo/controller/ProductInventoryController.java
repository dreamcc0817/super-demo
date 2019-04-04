package com.dreamcc.superdemo.controller;

import com.dreamcc.superdemo.model.ProductInventory;
import com.dreamcc.superdemo.request.ProductInventoryCacheRefreshRequest;
import com.dreamcc.superdemo.request.ProductInventoryDBUpdateRequest;
import com.dreamcc.superdemo.request.RequestDemo;
import com.dreamcc.superdemo.service.ProductInventoryService;
import com.dreamcc.superdemo.service.RequestAsyncProcessService;
import com.dreamcc.superdemo.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/3 17:26
 * @Version: V1.0
 */
@Slf4j
@RestController
public class ProductInventoryController {

	private RequestAsyncProcessService requestAsyncProcessService;

	private ProductInventoryService productInventoryService;

	@PutMapping("/updateProductInventory")
	public ResponseVo updateProductInventory(ProductInventory productInventory) {

		log.info("================日志=================");
		log.info("接受到商品更新商品库存的请求，商品id：{},商品库存：{}", productInventory.getProductId(), productInventory.getInvertoryCnt());

		ResponseVo responseVo;
		try {
			RequestDemo requestDemo = new ProductInventoryDBUpdateRequest(productInventory, productInventoryService);
			requestAsyncProcessService.process(requestDemo);
			responseVo = new ResponseVo(ResponseVo.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseVo = new ResponseVo(ResponseVo.FAILURE);
		}
		return responseVo;
	}

	@GetMapping("/getProductInventory")
	public ProductInventory getProductInventory(Integer productId) {

		log.info("================日志=================");
		log.info("接收到一个商品读请求，商品id：{}",productId);

		ProductInventory productInventory;

		try {
			RequestDemo requestDemo = new ProductInventoryCacheRefreshRequest(productId, productInventoryService,false);
			requestAsyncProcessService.process(requestDemo);

			Long startTime = System.currentTimeMillis();
			Long endTime = 0L;
			Long waitTime = 0L;

			while (true) {
				if (waitTime > 200) {
					break;
				}

				productInventory = productInventoryService.getProductInventoryCache(productId);

				if (productInventory != null) {
					log.info("================日志=================");
					log.info("在200ms内读到了redis中的库存缓存，商品id：{},库存数量：{}",productId,productInventory.getInvertoryCnt());
					return productInventory;
				} else {
					Thread.sleep(20);
					endTime = System.currentTimeMillis();
					waitTime = endTime - startTime;
				}
			}
			productInventory = productInventoryService.findProductInventory(productId);
			if (productInventory != null) {
				requestDemo = new ProductInventoryCacheRefreshRequest(productId,productInventoryService,true);
				requestAsyncProcessService.process(requestDemo);
				return productInventory;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ProductInventory(productId, -1L);
	}
}
