package com.hotel.pojo.vo;

import lombok.Data;

@Data
public class RoomOrderVo {

    // 房间编号
    private String RoomNumber;

    // 客户姓名
    private String CustomerName;

    // 来源
    private String Source;

    // 入住日期
    private String CheckinDate;

    // 退房日期
    private String CheckoutDate;

    // 房间状态
    private String RoomStatus;
}
