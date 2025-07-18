package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.mapper.CustomerInfoMapper;
import com.hotel.mapper.ProductOrderMapper;
import com.hotel.pojo.CustomerInfo;
import com.hotel.pojo.ProductOrder;
import com.hotel.pojo.dto.ProductOrderDto;
import com.hotel.pojo.vo.ProductOrderVo;
import com.hotel.service.ProductOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder>
        implements ProductOrderService {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Override
    public Page<ProductOrderVo> findProductOrder(String orderTime, String productName, Integer status,
                                                 Integer pageNum, Integer pageSize) {
        Page<ProductOrderVo> page = new Page<>(pageNum, pageSize);
        Page<ProductOrderVo> result = productOrderMapper.findProductOrder(page, orderTime, productName, status);
        return result;
    }

    @Override
    public Boolean updateProductOrderStatus(Long id, Integer status) {
        Boolean result = productOrderMapper.updateProductOrderStatus(id, status);
        return result;
    }

    @Override
    @Transactional
    public Boolean addProductOrder(ProductOrderDto productOrderDto) {
        // 先插入商品订单表
        ProductOrder productOrder = new ProductOrder();
        BeanUtils.copyProperties(productOrderDto, productOrder);
        productOrder.setOrderDate(productOrderDto.getOrderDate());
        productOrderMapper.insert(productOrder);
        // 再插入客户表
        // 如果客户是新客户，插入客户表
        if (customerInfoMapper.selectById(productOrderDto.getCustomerId()) == null) {
            CustomerInfo customer = new CustomerInfo();
            BeanUtils.copyProperties(productOrderDto, customer);
            customerInfoMapper.insert(customer);
        }
        return true;
    }
}
