package com.hotel.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class RoomTypeVo {

    private Long id;

    // 房型名称
    private String typeName;

    // 房型描述
    private String description;

    // 房型图片
    private String img;

    // 房间总数
    private Integer totalRooms;
}
