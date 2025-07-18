package com.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    // 自增
    @TableId(type = IdType.AUTO)
    private Long id;          // 订单ID
    private Long customerId;  // 顾客ID
    private Long productId;   // 商品ID
    private Integer quantity; // 购买数量
    private Integer status;   // 状态：0-待支付 1-已支付 2-已取消
    private Date orderDate; // 订单日期

}