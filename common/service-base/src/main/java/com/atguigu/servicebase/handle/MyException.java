package com.atguigu.servicebase.handle;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 自定义异常类
 */
@Data
@AllArgsConstructor
public class MyException extends RuntimeException{
    private Integer code;
    private String message;
}
