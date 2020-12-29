package com.guli.oss.controller;

import com.atguigu.commonutils.Result;
import com.guli.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Api("阿里云文件管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/oss/file")
public class FileUploadController {
    @Autowired
    FileService fileService;
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file){
        String upload = fileService.upload(file);

        return Result.ok().message("文件上传成功").data("uploadUrl",upload);
    }
}
