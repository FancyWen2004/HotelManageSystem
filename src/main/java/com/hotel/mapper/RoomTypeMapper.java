package com.hotel.mapper;

import com.hotel.pojo.RoomType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.pojo.vo.RoomTypeVo;

import java.util.List;

/**
* @author WenHua
* @description 针对表【room_type(酒店房型配置表)】的数据库操作Mapper
* @createDate 2025-07-07 22:40:33
* @Entity com.hotel.pojo.RoomType
*/
public interface RoomTypeMapper extends BaseMapper<RoomType> {

    List<RoomTypeVo> getAllRoomTypesWithRoomCount();
}




