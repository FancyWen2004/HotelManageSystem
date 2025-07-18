package com.hotel.controller.RoomStatusController;

import com.hotel.common.Result;
import com.hotel.pojo.RoomInfo;
import com.hotel.pojo.RoomType;
import com.hotel.pojo.vo.RoomOrderVo;
import com.hotel.pojo.vo.RoomTypeVo;
import com.hotel.service.OrderMainService;
import com.hotel.service.RoomInfoService;
import com.hotel.service.RoomTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "房间状态")
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private OrderMainService orderMainService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomInfoService roomInfoService;

    /**
     * @Description: 查询所有房型
     * @Author: Wen
     * @Date: 2025/7/11 19:03
     * @Param: null
     */
    @Operation(summary = "查询所有房型信息")
    @GetMapping("/getAllRoomTypes")
    public Result getAllRoomTypes() {
        // 1.查询所有房型
        // 1.1 需要根据查出的房型来查找下面对应的房间出租信息
        List<RoomType> roomTypeList = roomTypeService.list();
        return Result.success(roomTypeList);
    }

    /**
     * @Description: 根据房间类型id查询房间号
     * @Author: Wen
     * @Date: 2025/7/11 19:04
     * @Param: roomTypeId
     */
    // 查询房间类型对应的所有房间号
    @Operation(summary = "查询房间类型对应的所有房间号")
    @GetMapping("/getAllRoomNumbersByRoomTypeId")
    public Result getAllRoomNumbersByRoomTypeId(@RequestParam Long roomTypeId) {
        // 1.根据房间类型id查询房间号
        // 1.1 根据房间类型id查询房间id
        // 1.2 根据房间id查询对应房间号
        List<RoomInfo> roomInfos = roomInfoService.findByRoomTypeId(roomTypeId);
        return Result.success(roomInfos);
    }

    /**
     * @Description: 查看某一天内的房间出租
     * @Author: Wen
     * @Date: 2025/7/11 19:04
     * @Param: date, roomTypeId
     */
    @Operation(summary = "查看某一天内的房间出租", description = "限定房间类型")
    @GetMapping("/saleStatusInOneDay")
    public Result saleStatusInOneDay(@RequestParam LocalDate date,
                                     @RequestParam Long roomTypeId) {
        // 1.根据时间和房型在订单表中查询客户id，入住时间，退房时间，房间id
        // 1.1 根据客户id查询客户信息：姓名，来源
        // 查询所有时间在时间范围内的订单，房间没有订单的也要返回，
        // 1.2 查询所有房间号和状态，房间id=>房间号=>房间状态
        // 3.返回房间信息和入住人信息:房间号，姓名，来源，入住时间，退房时间，房间状态。使用vo接收后返回
        List<RoomOrderVo> roomOrderVoList = orderMainService.saleStatusInOneDay(date, roomTypeId);
        return Result.success(roomOrderVoList);
    }

    @Operation(summary = "查看多日内的房间出租")
    @GetMapping("/saleStatusInPeriod")
    public Result saleStatusInPeriod(@RequestParam LocalDate startDate,
                                     @RequestParam LocalDate endDate,
                                     @RequestParam Long roomTypeId) {
        // 1.根据时间和房型查询处于在这个时间范围内的订单
        // 2.根据订单中的房间id查询房间被谁入住了
        // 3.返回房间号和入住人信息与房间状态和入住时间，退房时间
        // 返回数据需要：房间号，入住人姓名，入住人来源，入住时间，退房时间，房间状态
        List<RoomOrderVo> roomOrderVoList = orderMainService.saleStatusInPeriod(startDate, endDate, roomTypeId);
        return Result.success(roomOrderVoList);
    }

    /**
     * @Description: 查询所有房型的信息与该房型有多少个房间
     * @Author: Wen
     * @Date: 2025/7/12 14:11
     * @Param: null
     */
    @Operation(summary = "查询所有房型的信息与该房型有多少个房间")
    @GetMapping("/getAllRoomTypesWithRoomCount")
    public Result getAllRoomTypesWithRoomCount() {
        List<RoomTypeVo> result = roomTypeService.getAllRoomTypesWithRoomCount();
        return Result.success(result);
    }

    /**
     * @Description: 修改房型信息
     * @Author: Wen
     * @Date: 2025/7/13 15:41
     * @Param: roomType
     */
    @Operation(summary = "修改房型信息")
    @PostMapping("/updateRoomType")
    public Result updateRoomType(@RequestBody RoomType roomType) {
        boolean result = roomTypeService.updateById(roomType);
        if (result) {
            return Result.success();
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * @Description: 新增房间
     * @Author: Wen
     * @Date: 2025/7/14 11:24
     * @Param: roomInfo
    */
    @Operation(summary = "新增房间")
    @PostMapping("/addRoom")
    public Result addRoom(@RequestBody RoomInfo roomInfo){
        boolean result = roomInfoService.save(roomInfo);
        if (result){
            return Result.success();
        }else {
            return Result.error("房间信息有误");
        }
    }
}
