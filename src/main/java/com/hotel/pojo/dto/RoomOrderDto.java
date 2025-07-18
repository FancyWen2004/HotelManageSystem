package com.hotel.pojo.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class RoomOrderDto {

    // 房间号
    private String roomNumber;
    // 房间类型id
    private Long roomTypeId;
    // 入住日期
    private Date checkinDate;
    // 退房日期
    private Date checkoutDate;
    // 入住天数
    private Integer nights;
    // 渠道
    private String channel;
    // 早餐
    private Integer breakfast;
    // 订单状态
    private Integer orderStatus;
    // 订单金额
    private BigDecimal amount;
    // 支付方式
    private String paymentMethod;

    // 客户id
    private Long customerId;
    // 客户姓名
    private String customerName;
    // 客户手机号
    private String phone;
    // 客户身份证号
    private String idCard;
}
