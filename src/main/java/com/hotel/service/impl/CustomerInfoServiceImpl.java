package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.pojo.CustomerInfo;
import com.hotel.service.CustomerInfoService;
import com.hotel.mapper.CustomerInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author WenHua
* @description 针对表【customer_info(酒店客户信息表)】的数据库操作Service实现
* @createDate 2025-07-07 22:40:33
*/
@Service
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo>
    implements CustomerInfoService{

}




