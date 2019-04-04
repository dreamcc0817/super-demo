package com.dreamcc.superdemo.request;

import com.dreamcc.superdemo.model.ProductInventory;
import com.dreamcc.superdemo.service.ProductInventoryService;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.request
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/3 16:01
 * @Version: V1.0
 */
public class ProductInventoryDBUpdateRequest implements RequestDemo {
	/**
	 * 商品ID
	 */
	private ProductInventory productInventory;


	/**
	 * 商品库存service
	 */
	private ProductInventoryService productInventoryService;

	public ProductInventoryDBUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
		this.productInventory = productInventory;
		this.productInventoryService = productInventoryService;
	}


	@Override
	public void process() {
		//删除redis中的缓存
		productInventoryService.removeProductInventoryCache(productInventory);
		//修改数据库中的库存
		productInventoryService.updateProductInventory(productInventory);
	}

	/**
	 * 获取商品id
	 *
	 * @return 商品id
	 */
	@Override
	public Integer getProductId() {
		return productInventory.getProductId();
	}

	@Override
	public boolean isForceRefresh() {
		return false;
	}
}
