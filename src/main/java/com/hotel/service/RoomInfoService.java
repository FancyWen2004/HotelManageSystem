package com.hotel.service;

import com.hotel.pojo.RoomInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author WenHua
* @description 针对表【room_info(酒店房间信息表（含状态）)】的数据库操作Service
* @createDate 2025-07-07 22:40:33
*/
public interface RoomInfoService extends IService<RoomInfo> {

    List<RoomInfo> findByRoomTypeId(Long roomTypeId);
}
