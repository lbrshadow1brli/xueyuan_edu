package com.online.edu.eduservice.handler;

import com.online.edu.common.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    //1 对所有异常进行相同的处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().messsage("出现了异常");
    }

    //2 对特定的异常进行处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().messsage("0不能为除数，出现了空指针异常");
    }

    //3 自定义异常
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public R error(EduException e) {
        e.printStackTrace();
        return R.error().messsage(e.getMessage()).code(e.getCode());
    }
}
