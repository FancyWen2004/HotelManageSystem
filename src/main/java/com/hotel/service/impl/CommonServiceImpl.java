package com.hotel.service.impl;

import com.hotel.common.utils.RedisUtil;
import com.hotel.pojo.Turnover;
import com.hotel.service.CommonService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public String generateCode(String key, String email, HttpServletResponse response) {
        // 使用EasyCaptcha生成验证码
        Captcha captcha = new SpecCaptcha(130, 48, 4);
        captcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        String code = captcha.text().toLowerCase();
        // 记录验证码
        log.info("验证码：{}", code);
        return code;
    }

    @Override
    public boolean checkCode(String key, String code) {
        // 定义一个初始判定状态
        // 根据key从Redis中获取验证码
        Object redisCode = redisUtil.get("captcha:" + key);
        if (redisCode.equals(code)) {
            // 验证码正确，删除Redis中的验证码
            //redisTemplate.delete("captcha:" + key);
            return true;
        } else {
            log.error("验证码错误");
            return false;
        }
    }

    public void setCheckCode(String key, Captcha captcha) {
        String captchaBase64 = captcha.toBase64();
        // 将验证码和key存入Redis，设置过期时间为1分钟
        redisUtil.set(key, captchaBase64, 60);
    }

    @Override
    public String getCaptchaBase64(String key, String type) {
        String base64CheckCode = (String) redisUtil.get("captcha:" + type + ":base64:" + key);
        return base64CheckCode;
    }

}
