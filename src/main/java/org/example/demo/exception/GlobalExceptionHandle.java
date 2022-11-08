package org.example.demo.exception;


import org.example.demo.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author rosinate
 * @date 2022/11/4
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        System.out.println("全局....");
        return Result.fail().message("未知异常。");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e) {
        System.out.println("自定义异常....");
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }

}
