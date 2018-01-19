package com.wangchong.blog.controller;

import com.wangchong.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 评论
     * @param request
     * @param content
     * @param articleId
     * @param customerName
     * @return
     */
    @ResponseBody
    @RequestMapping("/comment.do")
    public Map<String,Object> comment(HttpServletRequest request,String content,Long articleId,String customerName){
        Map<String,Object> map = new HashMap<>();
        boolean result = commentService.comment(articleId,content,customerName);
        map.put("result",result);
        return map;
    }

    /**
     * 回复评论
     * @param request
     * @param reply
     * @return
     */
    @ResponseBody
    @RequestMapping("/replyComment.do")
    public Map<String,Object> replyComment(HttpServletRequest request,String reply){
        Map<String,Object> map = new HashMap<>();
        map.put("result",true);
        return map;
    }


}
