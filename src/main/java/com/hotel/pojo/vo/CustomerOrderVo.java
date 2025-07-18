package com.hotel.pojo.vo;

import lombok.Data;

@Data
public class CustomerOrderVo {
    // 客户名称
    private String customerName;
    // 身份证号
    private String idCard;
    // 房间号
    private String roomNumber;
    // 入住日期
    private String checkinDate;
    // 退房日期
    private String checkoutDate;
    // 订单金额
    private Double orderAmount;
    // 订单来源
    private String source;
    // 入驻时间
    private String night;
}
