package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.pojo.RoomInfo;
import com.hotel.service.RoomInfoService;
import com.hotel.mapper.RoomInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author WenHua
* @description 针对表【room_info(酒店房间信息表（含状态）)】的数据库操作Service实现
* @createDate 2025-07-07 22:40:33
*/
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
    implements RoomInfoService{

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Override
    public List<RoomInfo> findByRoomTypeId(Long roomTypeId) {
        List<RoomInfo> roomInfoList = roomInfoMapper.findByRoomTypeId(roomTypeId);
        return roomInfoList;
    }
}




