package com.hotel.common.exception;

import lombok.Getter;

/**
 * @Description: 错误码
 * @Author: Wen
 * @Date: 2025/7/13 12:14
 * @Param:
*/
@Getter
public enum ErrorCode {

    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    TOO_MANY_REQUEST(42900, "请求过于频繁"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败"),
    EMAIL_SEND_ERROR(50002, "邮件发送失败"),
    FiLE_UPLOAD_ERROR(50003, "文件上传失败"),
    MISSING_PARAMETER(50004, "缺少参数"),
    FILE_TRANSFER_ERROR(50005, "文件传输异常"),
    VERIFICATION_CODE_GENERATION_FAIL(50006,"验证码生成失败");

    // 状态码
    private final int code;

    // 状态信息
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
