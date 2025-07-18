package com.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * @TableName order_main
 */
@TableName(value ="order_main")
@Data
public class OrderMain {

    /** 订单ID（自增主键） */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 客户ID，关联 customer_info.id */
    private Long customerId;

    /** 所属酒店ID，默认 1 */
    private Long hotelId = 1L;

    /** 预订房型ID，关联 room_type.id */
    private Long roomTypeId;

    /** 实际入住房间ID（可为空） */
    private String roomNumber;

    /** 入住日期 */
    private Date checkinDate;

    /** 离店日期 */
    private Date checkoutDate;

    /** 入住天数 */
    private Integer nights;

    /** 预订渠道（如携程、美团） */
    private String channel;

    /**
     * 订单状态
     * 0-待支付
     * 1-已支付待入住
     * 2-已入住
     * 3-已完成
     * 4-退订
     */
    private Integer orderStatus;

    /** 订单创建时间 */
    private LocalDateTime createTime;

    /**
     * 是否含早餐
     * 0 含
     * 1 不含
     */
    private Integer breakfast;

    /** 费用金额 */
    private BigDecimal amount;

    /** 支付方式 */
    private String paymentMethod;

}