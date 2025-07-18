package com.hotel.pojo.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;
import java.util.Date;

@Data
public class OrderMainVo {
    // 房间类型名称
    @ExcelProperty("房间类型")
    private String RoomType;
    // 房间号
    @ExcelProperty("房间号")
    private String RoomNumber;
    // 订单编号
    @ExcelProperty("订单编号")
    private String orderSn;
    // 顾客姓名
    @ExcelProperty("顾客姓名")
    private String customerName;
    // 手机号
    @ExcelProperty("手机号")
    private String customerPhone;
    // 入住时间
    @ExcelProperty("入住时间")
    private Date checkinDate;
    // 退房时间
    @ExcelProperty("退房时间")
    private Date checkoutDate;
    // 晚数
    @ExcelProperty("晚数")
    private Integer nights;
    // 来源
    @ExcelProperty("来源")
    private String channel;
    // 是否含早
    @ExcelProperty("是否含早")
    private String isBreakfast;
    // 订单状态
    @ExcelProperty("订单状态")
    private Integer orderStatus;

}
