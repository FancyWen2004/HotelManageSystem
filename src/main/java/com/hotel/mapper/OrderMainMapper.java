package com.hotel.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotel.pojo.OrderMain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.pojo.Turnover;
import com.hotel.pojo.vo.CustomerOrderVo;
import com.hotel.pojo.vo.OrderMainVo;
import com.hotel.pojo.vo.RoomOrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
* @author WenHua
* @description 针对表【order_main(酒店订单主表)】的数据库操作Mapper
* @createDate 2025-07-07 22:40:33
* @Entity com.hotel.pojo.OrderMain
*/
@Mapper
public interface OrderMainMapper extends BaseMapper<OrderMain> {

    List<RoomOrderVo> saleStatusInPeriod(LocalDate startDate, LocalDate endDate, Long roomTypeId);

    List<RoomOrderVo> saleStatusInOneDay(LocalDate date, Long roomTypeId);

    Page<OrderMainVo> queryOrderInfo(Page<OrderMainVo> page , String roomType, String orderStatus, String roomNumber,
                                     String orderDate, String customerName, String customerPhone);

    Boolean updateOrderStatus(String orderSn, Integer orderStatus);

    Page<CustomerOrderVo> queryPageTime(Page<CustomerOrderVo> page, LocalDate checkinDate, LocalDate checkoutDate, String customerName, String customerPhone);

    Page<CustomerOrderVo> queryPageInfo(Page<CustomerOrderVo> page, String customerName, String customerPhone);

    List<Turnover> queryTurnover(Integer year);
}




