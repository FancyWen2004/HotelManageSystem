package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.pojo.OrderCost;
import com.hotel.service.OrderCostService;
import com.hotel.mapper.OrderCostMapper;
import org.springframework.stereotype.Service;

/**
* @author WenHua
* @description 针对表【order_cost(订单费用明细表（收入/支出）)】的数据库操作Service实现
* @createDate 2025-07-07 22:40:33
*/
@Service
public class OrderCostServiceImpl extends ServiceImpl<OrderCostMapper, OrderCost>
    implements OrderCostService{

}




