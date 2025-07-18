package com.hotel.service;

import com.hotel.pojo.Turnover;
import com.wf.captcha.base.Captcha;
import jakarta.servlet.http.HttpServletResponse;


public interface CommonService {

    String generateCode(String key, String email, HttpServletResponse response);

    boolean checkCode(String key, String code);

    // 从redis中获取验证码图片的base64编码
    String getCaptchaBase64(String key, String type);

    void setCheckCode(String key, Captcha captcha);

}
