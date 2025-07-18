package com.hotel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotel.pojo.OrderMain;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.pojo.Turnover;
import com.hotel.pojo.dto.RoomOrderDto;
import com.hotel.pojo.vo.CustomerOrderVo;
import com.hotel.pojo.vo.OrderMainVo;
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

    Page<OrderMainVo> queryOrderInfo(Page<OrderMainVo> page ,String roomType, String orderStatus, String roomNumber,
                                     String orderDate, String customerName, String customerPhone);

    boolean updateOrderStatus(String orderSn, Integer orderStatus);

    Page<CustomerOrderVo> queryPageCustomer(Page<CustomerOrderVo> page, LocalDate checkinDate, LocalDate checkoutDate,
                                            String customerName, String customerPhone);

    List<Turnover> queryTurnover(Integer year);

    boolean addRoomOrder(RoomOrderDto roomOrderDto);
}
