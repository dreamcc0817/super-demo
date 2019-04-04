package com.dreamcc.superdemo.mapper;

import com.dreamcc.superdemo.model.ProductInventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.mapper
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/3 10:46
 * @Version: V1.0
 */
@Mapper
public interface ProductInventoryMapper {
	/**
	 * 更新库存数量
	 *
	 * @param productInventory 商品库存
	 */
	void updateProdictInventory(ProductInventory productInventory);

	/**
	 * 根据商品id查询商品库存信息
	 *
	 * @param productId 商品id
	 * @return 商品库存信息
	 */
	ProductInventory findProductInventory(@Param("productId") Integer productId);
}
