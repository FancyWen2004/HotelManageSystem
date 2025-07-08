package com.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @TableName customer_info
 */
@TableName(value ="customer_info")
@Data
public class CustomerInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String phone;

    private String idCard;

    private Date registerTime;
}