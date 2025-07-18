package com.hotel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.pojo.ProductOrder;
import com.hotel.pojo.dto.ProductOrderDto;
import com.hotel.pojo.vo.ProductOrderVo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface ProductOrderService extends IService<ProductOrder> {
    Page<ProductOrderVo> findProductOrder(String orderTime, String productName, Integer status, Integer pageNum, Integer pageSize );

    Boolean updateProductOrderStatus(Long id, Integer status);

    Boolean addProductOrder(ProductOrderDto productOrderDto);
}
