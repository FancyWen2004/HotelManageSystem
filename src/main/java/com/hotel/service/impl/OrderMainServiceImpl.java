package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.pojo.OrderMain;
import com.hotel.pojo.vo.RoomOrderVo;
import com.hotel.service.OrderMainService;
import com.hotel.mapper.OrderMainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
* @author WenHua
* @description 针对表【order_main(酒店订单主表)】的数据库操作Service实现
* @createDate 2025-07-07 22:40:33
*/
@Service
public class OrderMainServiceImpl extends ServiceImpl<OrderMainMapper, OrderMain>
    implements OrderMainService{

    @Autowired
    private OrderMainMapper orderMainMapper;

    @Override
    public List<RoomOrderVo> saleStatusInPeriod(LocalDate startDate, LocalDate endDate, Long roomTypeId) {
        List<RoomOrderVo> list = orderMainMapper.saleStatusInPeriod(startDate,endDate,roomTypeId);
        return list;
    }

    @Override
    public List<RoomOrderVo> saleStatusInOneDay(LocalDate date, Long roomTypeId) {
        List<RoomOrderVo> list = orderMainMapper.saleStatusInOneDay(date,roomTypeId);
        return list;
    }
}




