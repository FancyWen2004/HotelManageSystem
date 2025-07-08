package com.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName room_info
 */
@TableName(value ="room_info")
@Data
public class RoomInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long hotelId;

    private Long roomTypeId;

    private String roomNumber;

    private String img;

    private Integer status;
}