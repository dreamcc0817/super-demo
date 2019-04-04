package com.dreamcc.superdemo.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Title: super-demo
 * @Package: com.dreamcc.superdemo.vo
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/4/3 17:06
 * @Version: V1.0
 */
@Data
@RequiredArgsConstructor
public class ResponseVo {
	/**
	 * 返回正确值
	 */
	public static final String SUCCESS = "success";
	/**
	 * 返回错误值
	 */
	public static final String FAILURE = "failure";

	private String status;

	private String message;

	public ResponseVo(String status) {
		this.status = status;
	}
}
