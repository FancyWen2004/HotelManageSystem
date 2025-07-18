package com.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName order_cost
 */
@TableName(value ="order_cost")
@Data
public class OrderCost {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String orderId;

    private BigDecimal amount;

    private String paymentMethod;

    private Date paymentTime;

    private Integer status;
}