package com.wangchong.blog.controller;

import com.github.pagehelper.PageInfo;
import com.wangchong.blog.annotation.TraceLog;
import com.wangchong.blog.entity.Comment;
import com.wangchong.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    @TraceLog(name = "评论查询")
    @ResponseBody
    @RequestMapping("/ajaxCommentList.do")
    public Map<String,Object> ajaxQueryCommentList(HttpServletRequest request,Long articleId,Integer currPage,Integer pageSize,boolean flag){
        if(currPage == null){
            currPage = 1;
        }
        pageSize = 10;
        List<Comment> list = commentService.queryCommentList(articleId,1,currPage,pageSize,flag);
        PageInfo<Comment> page = new PageInfo<Comment>(list);
        int commentNums = commentService.queryCommentListCount(articleId,1);
        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        map.put("commentNums",commentNums);
        return map;
    }

    @ResponseBody
    @RequestMapping("/optComment.do")
    public Map<String,Object> ajaxOptComment(HttpServletRequest request,Long id,Integer status){
        Map<String,Object> map = new HashMap<>();
        boolean result = commentService.optComment(id,status);
        map.put("result",result);
        return map;
    }



}
