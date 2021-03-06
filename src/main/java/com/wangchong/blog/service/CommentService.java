package com.wangchong.blog.service;

import com.github.pagehelper.PageHelper;
import com.wangchong.blog.dao.ArticleDao;
import com.wangchong.blog.dao.CommentDao;
import com.wangchong.blog.entity.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ArticleDao articleDao;

    /**
     * 新增评论
     * @param articleId
     * @param content
     * @param customerName
     * @return
     */
    public boolean comment(Long articleId,String content,String customerName){
        Comment obj = new Comment();
        obj.setArticleId(articleId);
        obj.setContent(content);
        obj.setCreatetime(new Date());
        if(StringUtils.isBlank(customerName)){
            customerName = "游客";
        }
        obj.setCustomerName(customerName);
        obj.setStatus(1);
        boolean a = commentDao.insert(obj);
        boolean b = articleDao.addOpt2(articleId);
        return a && b;
    }


    /**
     * 根据id获取评论对象
     * @param id
     * @return
     */
    public Comment getComment(Long id){
        if(id != null){
            return commentDao.getById(id);
        }
        return null;
    }


    /**
     * 查询评论
     * @param articleId
     * @param currPage
     * @param Pagesize
     * @return
     */
    public List<Comment> queryCommentList(Long articleId,Integer status,Integer currPage,Integer Pagesize,boolean flag){
        Map<String,Object> map = new HashMap<>();
        map.put("articleId",articleId);
        map.put("status",status);
        if(flag){
            PageHelper.startPage(currPage,Pagesize);
        }
        return commentDao.queryList(map);
    }

    /**
     * 查询评论条数
     * @param articleId
     * @param status
     * @return
     */
    public int queryCommentListCount(Long articleId,Integer status){
        Map<String,Object> map = new HashMap<>();
        map.put("articleId",articleId);
        map.put("status",status);
        return commentDao.queryListCount(map);
    }

    /**
     * 更改评论状态
     * @param id
     * @param status
     * @return
     */
    public boolean optComment(Long id,Integer status){
        if(id == null || status == null){
            return false;
        }
        return commentDao.update(id,status);
    }

}
