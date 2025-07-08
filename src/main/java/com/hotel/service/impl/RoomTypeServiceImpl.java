package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.pojo.RoomType;
import com.hotel.service.RoomTypeService;
import com.hotel.mapper.RoomTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author WenHua
* @description 针对表【room_type(酒店房型配置表)】的数据库操作Service实现
* @createDate 2025-07-07 22:40:33
*/
@Service
public class RoomTypeServiceImpl extends ServiceImpl<RoomTypeMapper, RoomType>
    implements RoomTypeService{

}




