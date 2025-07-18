package com.hotel.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductOrderDto {

    // 顾客id
    private Long customerId;
    // 商品id
    private Long productId;
    // 商品数量
    private Integer quantity;
    // 订单状态
    private Integer status;
    // 顾客姓名
    private String name;
    // 手机号
    private String phone;
    // idCard
    private String idCard;
    // 下单时间
    private Date orderDate;

}
