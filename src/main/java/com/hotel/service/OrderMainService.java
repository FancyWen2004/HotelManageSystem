package com.hotel.service;

import com.hotel.pojo.OrderMain;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.pojo.vo.RoomOrderVo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
* @author WenHua
* @description 针对表【order_main(酒店订单主表)】的数据库操作Service
* @createDate 2025-07-07 22:40:33
*/
public interface OrderMainService extends IService<OrderMain> {

    List<RoomOrderVo> saleStatusInPeriod(LocalDate startDate, LocalDate endDate, Long roomTypeId);

    List<RoomOrderVo> saleStatusInOneDay(LocalDate date, Long roomTypeId);
}
