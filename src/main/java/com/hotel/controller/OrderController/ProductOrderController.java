package com.hotel.controller.OrderController;

import cn.idev.excel.FastExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotel.common.Result;
import com.hotel.common.exception.BusinessException;
import com.hotel.common.exception.ErrorCode;
import com.hotel.pojo.dto.ProductOrderDto;
import com.hotel.pojo.vo.OrderMainVo;
import com.hotel.pojo.vo.ProductOrderVo;
import com.hotel.service.ProductOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


/**
 * @Author Wen
 * @Description 【product_order(商品订单表)】相关的数据库操作
 * @Date 2025-07-07 22:40:33
 */
@Tag(name = "商品订单管理")
@RestController
@CrossOrigin
@RequestMapping("/productOrder")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    /**
     * @Description: 根据选填的时间和选填的商品种类查询商品订单
     * @Author: Wen
     * @Date: 2025/7/13 14:13
     * @Param:
     */
    @Operation(summary = "根据选填的时间和选填的商品种类查询商品订单")
    @GetMapping("/findProductOrder")
    public Result findProductOrder(@RequestParam(required = false) String OrderTime,
                                   @RequestParam(required = false) String productName,
                                   @RequestParam(required = false) Integer status,
                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<ProductOrderVo> result = productOrderService.findProductOrder(OrderTime, productName, status, pageNum, pageSize);
        return Result.success(result);
    }

    /**
     * @Description: 实现根据传入的订单id和状态修改订单状态
     * @Author: Wen
     * @Date: 2025/7/13 15:23
     * @Param:
     */
    @Operation(summary = "实现根据传入的订单id和状态修改订单状态")
    @GetMapping("/updateProductOrderStatus")
    public Result updateProductOrderStatus(@RequestParam Long id,
                                           @RequestParam Integer status) {
        Boolean result = productOrderService.updateProductOrderStatus(id, status);
        if (result) {
            return Result.success();
        } else {
            return Result.error("修改商品订单状态失败");
        }
    }

    /**
     * @Description: 导出对应条件的商品订单Excel
     * @Author: Wen
     * @Date: 2025/7/13 19:31
     * @Param:
     */
    @Operation(summary = "导出对应条件的商品订单Excel")
    @GetMapping("/exportProductExcel")
    public void exportProductExcel(@RequestParam(required = false) String OrderTime,
                                     @RequestParam(required = false) String productName,
                                     @RequestParam(required = false) Integer status,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     HttpServletResponse response) {
        Page<ProductOrderVo> result = productOrderService.findProductOrder(OrderTime, productName, status, pageNum, pageSize);
        List<ProductOrderVo> list = result.getRecords();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        try {
            String name = URLEncoder.encode("商品订单", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + name + ".xlsx");
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.FILE_TRANSFER_ERROR);
        }
        try {
            FastExcel.write(response.getOutputStream(), ProductOrderVo.class).sheet("模板").doWrite(list);
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_TRANSFER_ERROR);
        }
    }

    /**
     * @Description: 根据ProductOrderDto添加商品订单
     * @Author: Wen
     * @Date: 2025/7/13 20:07
     * @Param:
     */
    @Operation(summary = "添加商品订单")
    @PostMapping("/addProductOrder")
    public Result addProductOrder(@RequestBody ProductOrderDto productOrderDto) {
        Boolean result = productOrderService.addProductOrder(productOrderDto);
        if (result) {
            return Result.success();
        } else {
            return Result.error("添加商品订单失败");
        }
    }

    /**
     * @Description: 根据订单id删除订单
     * @Author: Wen
     * @Date: 2025/7/13 22:26
     * @Param:
     */
    @Operation(summary = "根据订单id删除订单")
    @DeleteMapping("/deleteProductOrder/{id}")
    public Result deleteProductOrder(@PathVariable Long id) {
        boolean result = productOrderService.removeById(id);
        if (result) {
            return Result.success();
        } else {
            return Result.error("删除商品订单失败");
        }
    }

}
