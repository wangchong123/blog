package com.wangchong.blog.controller;

import com.wangchong.blog.entity.Type;
import com.wangchong.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 条件查询
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/typeList.do")
    public Map<String,Object> queryTypeList(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        List<Type> list = typeService.listType();
        map.put("list",list);
        return map;
    }

    /**
     * 查询每个分类下的文章数量
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryTypeNums.do")
    public Map<String,Object> queryTypeNums(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        List<Type> list = typeService.queryTypeList();
        map.put("list",list);
        return map;
    }
}
