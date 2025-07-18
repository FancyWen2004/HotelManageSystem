package com.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.mapper.CustomerInfoMapper;
import com.hotel.mapper.OrderCostMapper;
import com.hotel.mapper.RoomInfoMapper;
import com.hotel.pojo.*;
import com.hotel.pojo.dto.RoomOrderDto;
import com.hotel.pojo.vo.CustomerOrderVo;
import com.hotel.pojo.vo.OrderMainVo;
import com.hotel.pojo.vo.RoomOrderVo;
import com.hotel.service.OrderMainService;
import com.hotel.mapper.OrderMainMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        implements OrderMainService {

    @Autowired
    private OrderMainMapper orderMainMapper;

    @Autowired
    private OrderCostMapper orderCostMapper;

    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Override
    public List<RoomOrderVo> saleStatusInPeriod(LocalDate startDate, LocalDate endDate, Long roomTypeId) {
        List<RoomOrderVo> list = orderMainMapper.saleStatusInPeriod(startDate, endDate, roomTypeId);
        return list;
    }

    @Override
    public List<RoomOrderVo> saleStatusInOneDay(LocalDate date, Long roomTypeId) {
        List<RoomOrderVo> list = orderMainMapper.saleStatusInOneDay(date, roomTypeId);
        return list;
    }

    @Override
    public Page<OrderMainVo> queryOrderInfo(Page<OrderMainVo> page, String roomType, String orderStatus, String roomNumber, String orderDate,
                                            String customerName, String customerPhone) {
        Page<OrderMainVo> result = orderMainMapper.
                queryOrderInfo(page, roomType, orderStatus, roomNumber,
                        orderDate, customerName, customerPhone);
        return result;
    }

    @Override
    public boolean updateOrderStatus(String orderSn, Integer orderStatus) {
        Boolean result = orderMainMapper.updateOrderStatus(orderSn, orderStatus);
        return result;
    }

    @Override
    public Page<CustomerOrderVo> queryPageCustomer(Page<CustomerOrderVo> page, LocalDate checkinDate, LocalDate checkoutDate,
                                                   String customerName, String customerPhone) {
        Page<CustomerOrderVo> result = orderMainMapper.queryPageTime(page, checkinDate, checkoutDate, customerName, customerPhone);
        return result;
    }

    @Override
    public List<Turnover> queryTurnover(Integer year) {
        List<Turnover> list = orderMainMapper.queryTurnover(year);
        return list;
    }

    @Transactional
    @Override
    public boolean addRoomOrder(RoomOrderDto roomOrderDto) {
        // 给订单主表添加记录
        OrderMain orderMain = new OrderMain();
        BeanUtils.copyProperties(roomOrderDto, orderMain);
        orderMainMapper.insert(orderMain);
        // 根据客户id查询客户是否存在，不存在就添加
        if (customerInfoMapper.selectById(roomOrderDto.getCustomerId()) == null) {
            CustomerInfo customerInfo = new CustomerInfo();
            BeanUtils.copyProperties(roomOrderDto, customerInfo);
            customerInfo.setName(roomOrderDto.getCustomerName());
            customerInfoMapper.insert(customerInfo);
        }
        // 根据传入的订单时间判断是否要改变房间状态
        // 如果订单的退房时间大于当前时间，就改变房间状态为2(已预定)
        if (roomOrderDto.getCheckoutDate().after(new Date())) {
            LambdaUpdateWrapper<RoomInfo> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(RoomInfo::getRoomNumber, roomOrderDto.getRoomNumber())
                    .set(RoomInfo::getStatus, 2);
            roomInfoMapper.update(null, updateWrapper);
        }
        return true;
    }
}




