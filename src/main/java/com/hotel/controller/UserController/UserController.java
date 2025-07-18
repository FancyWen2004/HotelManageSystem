package com.hotel.controller.UserController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hotel.common.exception.BusinessException;
import com.hotel.common.exception.ErrorCode;
import com.hotel.common.utils.RedisUtil;
import com.hotel.pojo.User;
import com.hotel.pojo.dto.UserDto;
import com.hotel.service.CommonService;
import com.hotel.service.UserService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.hotel.common.Result;

@Tag(name = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    /**
     * @Description: 根据key 获取验证码
     * @Author: Wen
     * @Date: 2025/7/11 19:03
     * @Param: key
    */
    @GetMapping("/getLoginCode")
    @Operation(summary = "登陆时获取验证码")
    // 通过request返回验证码图片，key为验证码的key，用于校验验证码
    public Result getLoginCode(@RequestParam String key,
                               HttpServletResponse response) {
        // 使用EasyCaptcha生成验证码
        Captcha captcha = new SpecCaptcha(130, 48, 4);
        String code = captcha.text().toLowerCase();
        // 根据传入key在redis中存入验证码
        redisUtil.set("captcha:login:" + key, code, 60);
        // redis存入经过base64编码的验证码图片
        //commonService.setCheckCode("captcha:login:base64:" + key, captcha);
        // 输出流
        try {
            captcha.out(response.getOutputStream());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.VERIFICATION_CODE_GENERATION_FAIL);
        }
        return Result.success();
    }

    /**
     * @Description: 用户登录
     * @Author: Wen
     * @Date: 2025/7/11 19:03
     * @Param: userDto, key, checkCode
    */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody @Validated UserDto userDto,
                        @RequestParam String key,
                        @RequestParam String checkCode){
        // 把传入的验证码转成小写字符
        String lowCheckCode = checkCode.toLowerCase();
        // 校验验证码
        String redisCode = (String) redisUtil.get("captcha:login:" + key);
        if (!lowCheckCode.equals(redisCode)) {
            // 验证码错误，返回错误信息
            return Result.error("验证码错误");
        }
        // 根据传入手机号进行查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone,userDto.getPhone());
        User user = userService.getOne(queryWrapper);
        // 判断是否查询到
        if(user == null){
            // 没有查询到，返回错误信息
            return Result.error("手机号或密码错误");
        }
        // 比对密码
        if(!user.getPassword().equals(userDto.getPassword())){
            // 密码错误，返回错误信息
            return Result.error("手机号或密码错误");
        }
        // 登录成功，返回用户信息
        return Result.success("登陆成功");
    }

    /**
     * @Description: 注册
     * @Author: Wen
     * @Date: 2025/7/13 18:36
     * @Param: userDto
    */
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody @Validated UserDto userDto){
        // 根据账号查询，如果查询到，返回错误信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone,userDto.getPhone());

        User user = userService.getOne(queryWrapper);
        if(user != null){
            return Result.error("用户已存在");
        }else {
            User realUser = new User();
            BeanUtils.copyProperties(userDto, realUser);
            // 保存用户信息
            userService.save(realUser);
            return Result.success("注册成功");
        }
    }

}
