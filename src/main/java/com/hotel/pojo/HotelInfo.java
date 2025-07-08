package com.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName hotel_info
 */
@TableName(value ="hotel_info")
@Data
public class HotelInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String hotelName;

    private String address;

    private String contactPhone;
}