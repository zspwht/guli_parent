package com.guli.oss.client;

import com.atguigu.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("serviceedu")
@Component
public interface EduServiceClient {
    @GetMapping("/admin/edu/course/courseInfo/{id}")
    public Result searchCourseById(@PathVariable String id);
}
