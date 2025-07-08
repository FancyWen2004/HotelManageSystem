package com.hotel.mapper;

import com.hotel.pojo.RoomInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author WenHua
* @description 针对表【room_info(酒店房间信息表（含状态）)】的数据库操作Mapper
* @createDate 2025-07-07 22:40:33
* @Entity com.hotel.pojo.RoomInfo
*/
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    List<RoomInfo> findByRoomTypeId(Long roomTypeId);
}




