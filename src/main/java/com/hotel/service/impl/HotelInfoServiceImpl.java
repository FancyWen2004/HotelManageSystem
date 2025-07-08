package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.pojo.HotelInfo;
import com.hotel.service.HotelInfoService;
import com.hotel.mapper.HotelInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author WenHua
* @description 针对表【hotel_info(酒店基础信息表)】的数据库操作Service实现
* @createDate 2025-07-07 22:40:33
*/
@Service
public class HotelInfoServiceImpl extends ServiceImpl<HotelInfoMapper, HotelInfo>
    implements HotelInfoService{

}




