package com.hotel.controller.OrderController;

import cn.idev.excel.FastExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hotel.common.Result;
import com.hotel.common.exception.BusinessException;
import com.hotel.common.exception.ErrorCode;
import com.hotel.pojo.Turnover;
import com.hotel.pojo.dto.RoomOrderDto;
import com.hotel.pojo.vo.OrderMainVo;
import com.hotel.service.OrderMainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Description: 针对房间订单的数据库操作
 * @Author: Wen
 * @Date: 2025/7/12 09:23
 */
@Tag(name = "房间订单服务")
@RestController
@RequestMapping("/order")
public class RoomOrderController {

    @Autowired
    private OrderMainService orderMainService;

    /**
     * @Description: 根据传入的房型，订单状态，房间号，订购日期查询订单信息，客服名称和电话模糊查询
     * @Author: Wen
     * @Date: 2025/7/12 11:20
     * @Param: roomType, orderStatus, roomNumber, orderDate, customerName, customerPhone, pageNum, pageSize
     */
    @Operation(summary = "查询订单信息", description = "根据传入的房型，订单状态，房间号，订购日期查询订单信息，使用客户名称和电话模糊查询")
    @GetMapping("/queryOrderInfo")
    public Result queryOrderInfo(@RequestParam(required = false) String roomType,
                                 @RequestParam(required = false) String orderStatus,
                                 @RequestParam(required = false) String roomNumber,
                                 @RequestParam(required = false) String orderDate,
                                 @RequestParam(required = false) String customerName,
                                 @RequestParam(required = false) String customerPhone,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        // 1. 选填:房型，订单状态，房间号，订购日期，模糊查询:客服名称和电话
        // 2. 调用订单服务的查询订单信息接口
        // 3. 返回订单信息:房间号，房间类型，预定人信息，订单信息
        Page<OrderMainVo> page = new Page<>(pageNum, pageSize);
        Page<OrderMainVo> orderMainList = orderMainService.queryOrderInfo(page, roomType, orderStatus, roomNumber,
                orderDate, customerName, customerPhone);
        return Result.success(orderMainList);
    }

    /**
     * @Description: 导出订单的Excel文件
     * @Author: Wen
     * @Date: 2025/7/12 23:06
     * @Param:
     */
    @Operation(summary = "导出订单的Excel文件")
    @GetMapping("/excel")
    public void excel(@RequestParam(required = false) String roomType,
                      @RequestParam(required = false) String orderStatus,
                      @RequestParam(required = false) String roomNumber,
                      @RequestParam(required = false) String orderDate,
                      @RequestParam(required = false) String customerName,
                      @RequestParam(required = false) String customerPhone,
                      @RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize,
                      HttpServletResponse response
    ) {
        // 原来的接口就算带条件的查询，因此需要参入和原来一样的参数，然后查询出对应的数据，然后导出Excel文件
        Page<OrderMainVo> page = new Page<>(pageNum, pageSize);
        Page<OrderMainVo> orderMainList = orderMainService.queryOrderInfo(page, roomType, orderStatus, roomNumber,
                orderDate, customerName, customerPhone);
        // 取出分页数据中的真正的数据
        List<OrderMainVo> list = orderMainList.getRecords();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        try {
            String name = URLEncoder.encode("房间订单", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + name + ".xlsx");
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.FILE_TRANSFER_ERROR);
        }
        try {
            FastExcel.write(response.getOutputStream(), OrderMainVo.class).sheet("模板").doWrite(list);
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_TRANSFER_ERROR);
        }
    }

    /**
     * @Description: 修改订单状态，实现退订入住等功能
     * @Author: Wen
     * @Date: 2025/7/12 11:20
     * @Param: orderSn, orderStatus
     */
    @Operation(summary = "修改订单状态", description = "修改订单状态，实现退订入住等功能")
    @GetMapping("/updateOrderStatus")
    public Result updateOrderStatus(
            @RequestParam @NotEmpty(message = "Order not empty") String orderSn,
            @RequestParam @NotNull(message = "OrderStatus not empty") Integer orderStatus) {
        // 1. 订单状态是必填的
        // 2. 修改订单状态接口(订单状态：2-已入住、4-退订)
        boolean result = orderMainService.updateOrderStatus(orderSn, orderStatus);
        if (result) {
            return Result.success();
        } else {
            return Result.error("订单状态修改失败");
        }
    }

    /**
     * @Description: 根据订单时间查询当月的营业额
     * @Author: Wen
     * @Date: 2025/7/12 12:21
     * @Param:
     */
    @Operation(summary = "查询每个月的营业额", description = "根据传入年份查询当年每月的营业额")
    @GetMapping("/queryTurnover/{year}")
    public Result queryTurnover(@PathVariable @NotNull(message = "Year not empty") Integer year) {
        List<Turnover> turnoverList = orderMainService.queryTurnover(year);
        return Result.success(turnoverList);
    }

    /**
     * @Description: 实现新增房间订单
     * @Author: Wen
     * @Date: 2025/7/14 09:36
     * @Param:
     */
    @Operation(summary = "新增房间订单", description = "实现新增房间订单")
    @PostMapping("/addRoomOrder")
    public Result addRoomOrder(@RequestBody RoomOrderDto roomOrderDto) {
        // 调用订单服务的新增房间订单接口
        boolean result = orderMainService.addRoomOrder(roomOrderDto);
        if (result) {
            return Result.success();
        } else {
            return Result.error("房间订单新增失败");
        }
    }
}
