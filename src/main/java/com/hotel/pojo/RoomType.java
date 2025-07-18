package com.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName room_type
 */
@TableName(value ="room_type")
@Data
public class RoomType {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long hotelId;

    private String typeName;

    private String description;

    private String img;

}