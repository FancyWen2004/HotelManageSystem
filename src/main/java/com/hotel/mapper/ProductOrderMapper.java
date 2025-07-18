package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotel.pojo.ProductOrder;
import com.hotel.pojo.vo.ProductOrderVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductOrderMapper extends BaseMapper<ProductOrder> {

    Page<ProductOrderVo> findProductOrder(Page<ProductOrderVo> page, String orderTime, String productName, Integer status);

    Boolean updateProductOrderStatus(Long id, Integer status);
}
