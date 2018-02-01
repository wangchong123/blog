package com.wangchong.blog.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorPageController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String handleError(HttpServletRequest request, HttpServletResponse response){
        int code=response.getStatus();
        System.out.println(code);
        if(code == 500){
            return "/page/500";
        }else if(code == 404){
            return "/page/404";
        }else{
            return "/page/error";
        }

    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
