package com.example.demo.service;

import java.util.List;

import com.example.demo.model.OrderCommodity;

public interface OrderCommodityService {
	List <OrderCommodity> getAllOrderCommoditys();			//取得所有使用者資料
	List <OrderCommodity> getByCreated_atBetween(String start_at,String end_at);		//取得近期新增使用者

}
