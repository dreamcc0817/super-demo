package com.dreamcc.superdemo.request;

import com.dreamcc.superdemo.model.ProductInventory;
import com.dreamcc.superdemo.service.ProductInventoryService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.request
 * @Description: 重新加载商品库存的缓存
 * @Author: dreamcc
 * @Date: 2019/4/3 15:50
 * @Version: V1.0
 */
@Slf4j
public class ProductInventoryCacheRefreshRequest implements RequestDemo {

	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 商品库存service
	 */
	private ProductInventoryService productInventoryService;

	/**
	 * 是否强制刷新缓存
	 */
	private boolean forceRefresh;

	public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService, boolean forceRefresh) {
		this.productId = productId;
		this.productInventoryService = productInventoryService;
		this.forceRefresh = forceRefresh;
	}


	@Override
	public void process() {
		//从数据库中查询最新的商品库存数量
		ProductInventory productInventory = productInventoryService.findProductInventory(productId);
		log.info("===================日志====================");
		log.info("已查询到最新库存数量，商品id:{},商品库存数量：{}",productId,productInventory.getInvertoryCnt());
		//将最新的商品库存数量，刷新到redis缓存中去
		productInventoryService.setProductInventoryCache(productInventory);
	}

	/**
	 * 获取商品id
	 *
	 * @return 商品id
	 */
	@Override
	public Integer getProductId() {
		return productId;
	}

	@Override
	public boolean isForceRefresh() {
		return forceRefresh;
	}
}
