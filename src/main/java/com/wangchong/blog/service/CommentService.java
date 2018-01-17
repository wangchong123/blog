package com.wangchong.blog.service;

import com.wangchong.blog.dao.ArticleDao;
import com.wangchong.blog.dao.CommentDao;
import com.wangchong.blog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ArticleDao articleDao;

    public boolean comment(Long articleId,String content,String customerName){
        Comment obj = new Comment();
        obj.setArticleId(articleId);
        obj.setContent(content);
        obj.setCreatetime(new Date());
        obj.setCustomerName(customerName);
        obj.setStatus(1);
        boolean a = commentDao.insert(obj);
        boolean b = articleDao.addOpt2(articleId);
        return a && b;
    }

    public boolean replyComment(String reply,Long id){
        if(id != null && reply != null){
            Comment comment = getComment(id);
            if(comment != null){
                comment.setStatus(2);
                comment.setReply(reply);
                comment.setReplytime(new Date());
                return commentDao.update(comment);
            }
        }
        return false;
    }

    public Comment getComment(Long id){
        if(id != null){
            return commentDao.getById(id);
        }
        return null;
    }

}
