package com.online.edu.eduservice.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//自动生成有参、无参构造
@NoArgsConstructor
@AllArgsConstructor

public class EduException extends RuntimeException {

    private Integer code;

    private String message;

}
