package com.dreamcc.superdemo.service.impl;

import com.dreamcc.superdemo.dao.RedisDAO;
import com.dreamcc.superdemo.mapper.ProductInventoryMapper;
import com.dreamcc.superdemo.model.ProductInventory;
import com.dreamcc.superdemo.service.ProductInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.service.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/3 14:47
 * @Version: V1.0
 */
@Slf4j
@Service("productInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryService {

	private ProductInventoryMapper productInventoryMapper;

	private RedisDAO redisDAO;

	@Autowired
	public ProductInventoryServiceImpl(ProductInventoryMapper productInventoryMapper, RedisDAO redisDAO) {
		this.productInventoryMapper = productInventoryMapper;
		this.redisDAO = redisDAO;
	}

	@Override
	public void updateProductInventory(ProductInventory productInventory) {
		productInventoryMapper.updateProdictInventory(productInventory);
		log.info("已修改的数据库中的库存，商品id：{},商品库存：{}", productInventory.getProductId(), productInventory.getInvertoryCnt());
	}

	@Override
	public void removeProductInventoryCache(ProductInventory productInventory) {
		String key = "product:inventory:" + productInventory.getProductId();
		redisDAO.delete(key);
		log.info("删除Redis中的缓存，key：{}", key);
	}

	@Override
	public ProductInventory findProductInventory(Integer productId) {
		ProductInventory productInventory = productInventoryMapper.findProductInventory(productId);
		return productInventory;
	}

	@Override
	public void setProductInventoryCache(ProductInventory productInventory) {
		String key = "product:inventory:" + productInventory.getProductId();
		redisDAO.set(key, String.valueOf(productInventory.getInvertoryCnt()));
		log.info("以更新商品库存的缓存，商品id：{}，商品库存:{}", productInventory.getProductId(), productInventory.getInvertoryCnt());
	}

	@Override
	public ProductInventory getProductInventoryCache(Integer productId) {
		Long inventoryCnt = 0L;
		String key = "product:inventory:" + productId;
		String result = redisDAO.get(key);
		if (result != null && !"".equals(result)) {
			try {
				inventoryCnt = Long.valueOf(result);
				return new ProductInventory(productId, inventoryCnt);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
