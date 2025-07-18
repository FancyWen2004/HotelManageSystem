package com.hotel.controller.CommonController;

import com.hotel.common.Result;
import com.hotel.service.CommonService;
import com.hotel.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 通用的功能实现方法
 * @Author: Wen
 * @Date: 2025/7/12 21:50
*/
@Tag(name = "通用接口")
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private FileService fileService;

    /**
     * @Description: TODO: 实现文件上传
     * @Author Wen
     * @Date 2025/3/25 20:14
     * @Param file
     */
    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public Result upload(@Parameter(description = "上传的文件", required = true)
                         @RequestPart MultipartFile file) {
        String url = fileService.upload(file);
        log.info("上传文件地址：{}", url);
        return Result.success(url);
    }

    /**
     * @Description: TODO: 使用FastExcel实现导出Excel文件
     * @Description: TODO: 分别在订单查询中实现
     * @Author: Wen
     * @Date: 2025/7/12 21:50
     * @Param:
    */
//    @Operation(summary = "导出Excel文件")
//    @PostMapping("/exportExcel")
//    public Result exportExcel() {
//        // 1. 调用订单服务的导出Excel文件接口
//        // 2. 返回Excel文件
//        return Result.success();
//    }

}
