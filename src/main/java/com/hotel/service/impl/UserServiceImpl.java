package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.mapper.UserMapper;
import com.hotel.pojo.User;
import com.hotel.service.UserService;
import org.springframework.stereotype.Service;

// 使用mybatisplus进行标记
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
}
