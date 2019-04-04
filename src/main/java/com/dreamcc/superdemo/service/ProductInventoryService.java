package com.dreamcc.superdemo.service;

import com.dreamcc.superdemo.model.ProductInventory;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.service
 * @Description: 商品库存Service接口
 * @Author: dreamcc
 * @Date: 2019/4/3 14:39
 * @Version: V1.0
 */
public interface ProductInventoryService {

	/**
	 * 更新商品库存
	 *
	 * @param productInventory 商品库存
	 */
	void updateProductInventory(ProductInventory productInventory);

	/**
	 * 删除Redis中的商品库存的缓存
	 *
	 * @param productInventory 商品库存
	 */
	void removeProductInventoryCache(ProductInventory productInventory);

	/**
	 * 根据商品id查询商品库存
	 *
	 * @param productId 商品id
	 * @return 商品库存
	 */
	ProductInventory findProductInventory(Integer productId);

	/**
	 * 设置商品库存的缓存
	 *
	 * @param productInventory 商品库存
	 */
	void setProductInventoryCache(ProductInventory productInventory);

	/**
	 * 获取商品库存的缓存
	 *
	 * @param productId 商品id
	 * @return 库存商品
	 */
	ProductInventory getProductInventoryCache(Integer productId);
}
