package com.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @TableName order_main
 */
@TableName(value ="order_main")
@Data
public class OrderMain {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String orderSn;

    private Long customerId;

    private Long hotelId;

    private Long roomTypeId;

    private Long roomId;

    private Date checkinDate;

    private Date checkoutDate;

    private Integer nights;

    private String channel;

    private Integer isBreakfast;

    private Integer orderStatus;

    private Date createTime;

}