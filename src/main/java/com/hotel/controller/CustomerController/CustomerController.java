package com.hotel.controller.CustomerController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotel.common.Result;
import com.hotel.pojo.vo.CustomerOrderVo;
import com.hotel.service.OrderMainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

/**
 * @Description: 针对客户相关的数据库操作
 * @Author: Wen
 * @Date: 2025/7/12 09:23
 */
@Tag(name = "客户服务")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private OrderMainService orderMainService;

    /**
     * @Description: 根据传入条件动态查询客户信息
     * @Author: Wen
     * @Date: 2025/7/12 09:12
     * @Param:
     */
    @Operation(summary = "分页查询所有客户信息", description = "可以通过入住时间，退房时间来筛选返回的信息，还可以通过手机号和客户名来模糊查询")
    @GetMapping("/queryPageCustomer")
    public Result<Page<CustomerOrderVo>> queryPageCustomer(@RequestParam(required = false) LocalDate checkinDate,
                                                           @RequestParam(required = false) LocalDate checkoutDate,
                                                           @RequestParam(required = false) String customerName,
                                                           @RequestParam(required = false) String customerPhone,
                                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        // 分页查询所有客户信息
        // 可以通过入住时间，退房时间来筛选返回的信息，还可以通过手机号和客户名来模糊查询
        // 返回的信息即：CustomerOrderVo 包含：客户名，手机号，入住时间，退房时间，订单金额，订单号，房间号
        Page<CustomerOrderVo> page = new Page<>(pageNum, pageSize);
        Page<CustomerOrderVo> result = orderMainService.queryPageCustomer(page, checkinDate, checkoutDate, customerName, customerPhone);
        return Result.success(result);
    }

}
