package com.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @TableName room_status_calendar
 */
@TableName(value ="room_status_calendar")
@Data
public class RoomStatusCalendar {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long roomId;

    private Date date;

    private Integer status;
}