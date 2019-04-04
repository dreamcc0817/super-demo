package com.dreamcc.superdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.model
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/3 10:33
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventory {

	/**
	 * 商品ID
	 */
	private Integer productId;
	/**
	 * 库存数量
	 */
	private Long invertoryCnt;

}
