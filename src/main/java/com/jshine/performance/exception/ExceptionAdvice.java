package com.jshine.performance.exception;

import com.alibaba.fastjson.JSON;
import com.jshine.performance.utils.message.CodeEnum;
import com.jshine.performance.utils.message.ReturnMessageUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@ControllerAdvice
public class ExceptionAdvice {
    /*@ExceptionHandler(CustomException.class)
    @ResponseBody
    public ReturnMessage<Object> handle401(ShiroException e) {
        ReturnMessageUtil.error(CodeEnum.UNAUTHORIZED);
        return ReturnMessageUtil.error(CodeEnum.UNAUTHORIZED);
    }*/
    @ExceptionHandler({ CustomException.class })
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
        String ajaxHeader = request.getHeader("X-Requested-With");
        if (ajaxHeader.equals("X-Requested-With")) {
            // 输出JSON
           try {
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSON(ReturnMessageUtil.error(CodeEnum.UNAUTHORIZED)).toString());
                writer.flush();
                writer.close();
            }catch (Exception e){

            }

            return null;
        } else {
            return "redirect:/system/login";
        }
    }
}
