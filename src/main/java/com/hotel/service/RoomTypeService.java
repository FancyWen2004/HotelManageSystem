package com.hotel.service;

import com.hotel.pojo.RoomType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.pojo.vo.RoomTypeVo;

import java.util.List;

/**
* @author WenHua
* @description 针对表【room_type(酒店房型配置表)】的数据库操作Service
* @createDate 2025-07-07 22:40:33
*/
public interface RoomTypeService extends IService<RoomType> {

    List<RoomTypeVo> getAllRoomTypesWithRoomCount();
}
