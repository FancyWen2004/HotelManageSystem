package com.hotel.scheduled;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hotel.mapper.OrderMainMapper;
import com.hotel.mapper.RoomInfoMapper;
import com.hotel.pojo.OrderMain;
import com.hotel.pojo.RoomInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


/**
 * 定时任务：每天中午12点检查订单退房时间，更新房间状态
 * 定时任务：每小时检查订单退房时间，更新订单状态
 */
@Slf4j
@Component
public class ScheduledOrderStatus {

    @Autowired
    private OrderMainMapper orderMainMapper;

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Scheduled(cron = "0 0 12 * * ? ")
    @Transactional
    public void checkAndUpdateRoomStatus() {
        log.info("开始执行定时任务：检查订单中已经到了退房时间的房间，并更新房间状态");
        // 获取当前时间
        Date now = new Date();
        // 查询订单中退房日期小于当前日期并且订单状态为1(已支付待入住)2(已入住)3(已完成)的订单，将对应房间号的房间状态修改为1
        LambdaQueryWrapper<OrderMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 订单状态为1(已支付待入住)2(已入住)3(已完成)
        lambdaQueryWrapper.in(OrderMain::getOrderStatus, 1, 2, 3);
        lambdaQueryWrapper.lt(OrderMain::getCheckoutDate, now); // 退房日期小于当前日期
        List<OrderMain> orderMainList = orderMainMapper.selectList(lambdaQueryWrapper);
        // 使用lambda表达式筛选出订单中的房间号
        if (!orderMainList.isEmpty()) {
            List<String> roomNums = orderMainList.stream()
                    .map(OrderMain::getRoomNumber)
                    .toList();
            // 根据房间号批量更新房间信息
            UpdateWrapper<RoomInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("status", 1); // 房间状态为1
            updateWrapper.in("room_number", roomNums); // 房间号
            roomInfoMapper.update(null, updateWrapper);
        }
//        for (OrderMain orderMain : orderMainList) {
//            String roomNumber = orderMain.getRoomNumber();
//            // 根据roomId查询房间信息
//            RoomInfo room = roomInfoMapper.selectById(roomNumber);
//            // 更新房间状态
//            UpdateWrapper<RoomInfo> updateWrapper = new UpdateWrapper<>();
//            // 如果房间状态为2(已预定)3(已入住)，则更新为1
//            if (room.getStatus()== 2 || room.getStatus()== 3) {
//                updateWrapper.eq("room_number", room.getRoomNumber()); // 房间号
//                updateWrapper.set("status", 1); // 房间状态为1
//                roomInfoMapper.update(room, updateWrapper);
//            }
//        }
    }

    /**
     * @Description: TODO: 每一个小时执行定时任务，检查订单中已经到了退房时间的订单，并更新订单状态
     * @Author: Wen
     * @Date: 2025/7/17 14:20
     * @Param:
     */
    // 每分钟：0 0/1 * * * ?
    // 每小时：0 0 0/1 * * ?
    @Scheduled(cron = "0 0 0/1 * * ?")
    @Transactional
    public void checkAndUpdateRoomOrder() {
        log.info("开始执行定时任务：检查订单中已经到了退房时间的订单，并更新订单状态");
        // 获取当前时间
        Date now = new Date();
        // 查询订单中退房日期小于当前日期并且订单状态为1-已支付待入住、2-已入住的订单，更新订单状态为3-已完成
        LambdaQueryWrapper<OrderMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 订单状态为0-待支付、1-已支付待入住、2-已入住
        lambdaQueryWrapper.in(OrderMain::getOrderStatus, 1, 2);
        lambdaQueryWrapper.lt(OrderMain::getCheckoutDate, now); // 退房日期小于当前日期
        List<OrderMain> orderMainList = orderMainMapper.selectList(lambdaQueryWrapper);
        if (!orderMainList.isEmpty()) {
            // 批量修改订单状态为3-已完成
            // 使用lambda表达式筛选出订单中的订单号
            List<Long> orderIds = orderMainList.stream()
                    .map(OrderMain::getId)
                    .toList();
            // 批量更新订单状态
            UpdateWrapper<OrderMain> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("order_status", 3); // 订单状态为3(已完成)
            updateWrapper.in("id", orderIds); // 订单号
            orderMainMapper.update(null, updateWrapper);
        }
    }

    /**
     * @Description: TODO: 每一个小时查看到了入住时间的订单中的房间，把对应的房间状态修改成3(已入住)
     * @Author: Wen
     * @Date: 2025/7/17 18:20
     * @Param:
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    @Transactional
    public void checkAndUpdateRoomOrderStatus() {
        log.info("开始执行定时任务：检查订单中已经到了入住时间的订单，并更新订单状态");
        // 获取当前时间
        Date now = new Date();
        // 查看到了入住时间的订单中的房间，把对应的房间状态修改成3(已入住)
        // 查询入住时间小于等于当前时间的订单
        LambdaQueryWrapper<OrderMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 订单状态为1-已支付待入住、2-已入住
        lambdaQueryWrapper.in(OrderMain::getOrderStatus, 1, 2);
        lambdaQueryWrapper.le(OrderMain::getCheckinDate, now); // 入住日期小于等于当前日期
        List<OrderMain> orderMainList = orderMainMapper.selectList(lambdaQueryWrapper);
        if (!orderMainList.isEmpty()) {
            // 批量修改房间状态为3(已入住)
            // 使用lambda表达式筛选出订单中的房间号
            List<String> roomNums = orderMainList.stream()
                    .map(OrderMain::getRoomNumber)
                    .toList();
            // 根据房间号批量更新房间信息
            UpdateWrapper<RoomInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("status", 3); // 房间状态为3(已入住)
            updateWrapper.in("room_number", roomNums); // 房间号
            roomInfoMapper.update(null, updateWrapper);
        }
    }


    /**
     * @Description: TODO: 每十五分钟自动将订单状态为0(待支付)的订单状态更新为4(退订）
     * @Author: Wen
     * @Date: 2025/7/17 11:14
     * @Param:
     */
    @Scheduled(cron = "0 0/15 * * * ?")
    @Transactional
    public void checkAndUpdateOrderStatus() {
        log.info("开始执行定时任务：检查订单中状态为0-待支付的订单，更新订单状态");
        // 获取当前时间
        Date now = new Date();
        // 查询订单中退房日期小于当前日期并且订单状态为0-待支付的订单，将对应订单状态修改为4-退订
        LambdaQueryWrapper<OrderMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 订单状态为0-待支付
        lambdaQueryWrapper.eq(OrderMain::getOrderStatus, 0);
        lambdaQueryWrapper.lt(OrderMain::getCreateTime, now); // 订单创建时间小于当前时间
        List<OrderMain> orderMainList = orderMainMapper.selectList(lambdaQueryWrapper);
        if (!orderMainList.isEmpty()) {
            // 批量修改订单状态为4-退订
            // 使用lambda表达式筛选出订单中的订单号
            List<Long> orderIds = orderMainList.stream()
                    .map(OrderMain::getId)
                    .toList();
            // 批量更新订单状态
            UpdateWrapper<OrderMain> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("order_status", 4); // 订单状态为4
            updateWrapper.in("id", orderIds); // 订单号
            orderMainMapper.update(null, updateWrapper);
        }
    }

}