package com.hotel.pojo.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductOrderVo {
    // 订单id
    @ExcelProperty("订单id")
    private Long id;
    // 下单顾客姓名
    @ExcelProperty("顾客姓名")
    private String customerName;
    // 下单顾客手机号
    @ExcelProperty("手机号")
    private String phone;
    // 商品名称
    @ExcelProperty("商品名称")
    private String productName;
    // 商品单价
    @ExcelProperty("单价")
    private BigDecimal price;
    // 商品数量
    @ExcelProperty("数量")
    private Integer quantity;
    // 下单时间
    @ExcelProperty("下单时间")
    private Date orderDate;
    // 订单状态
    @ExcelProperty("订单状态")
    private String status;
}
