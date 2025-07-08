package com.hotel.mapper;

import com.hotel.pojo.OrderMain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.pojo.vo.RoomOrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.Date;
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
}




