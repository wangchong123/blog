package com.wangchong.blog.controller;

import com.wangchong.blog.entity.Pv;
import com.wangchong.blog.service.PvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/pv")
public class PvController {

    @Autowired
    private PvService pvService;


    /**
     * 记录pv
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/pvRecord.do")
    public Map<String,Object> pvRecord(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        boolean result = pvService.insert(request);
        map.put("result",result);
        return map;
    }

    /**
     * 获取pv统计
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPvByDate.do")
    public Map<String,Object> getPvByDate(){
        Map<String,Object> map = new HashMap<>();
        Pv pv = pvService.getByDate(new Date());
        map.put("pv",pv);
        return map;

    }
}
