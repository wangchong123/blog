package com.wangchong.blog.service;

import com.github.pagehelper.PageHelper;
import com.wangchong.blog.dao.ArticleDao;
import com.wangchong.blog.entity.Article;
import com.wangchong.blog.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 条件查询 分页
     * @param type
     * @param currPage
     * @param Pagesize
     * @return
     */
    public List<Article> queryArticleList(Long type,Integer currPage,Integer Pagesize){
        Map<String,Object> map = new HashMap<>();
        if(type != null){
            map.put("type",type);
        }
        PageHelper.startPage(currPage,Pagesize);
        return articleDao.queryList(map);
    }

    /**
     * 不分页
     * @return
     */
    public List<Article> queryArticleList(){
        Map<String,Object> map = new HashMap<>();
        return articleDao.queryList(map);
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public Article getArticle(Long id){
        if(id != null){
            return articleDao.getById(id);
        }
        return null;
    }

    /**
     * 更新对文章的操作
     * @param id
     * @param opt
     */
    public void updateOpt(Long id,Integer opt){
        if(id != null && opt != null){
            if(opt.equals(ConstantUtil.OPT1)){ //赞
                articleDao.addOpt1(id);
            }else if(opt.equals(ConstantUtil.OPT2)){ //评论
                articleDao.addOpt2(id);
            }else if(opt.equals(ConstantUtil.OPT3)){ //浏览
                articleDao.addOpt3(id);
            }
        }
    }

    /**
     * 新增
     * @param title
     * @param describe
     * @param content
     * @param type
     * @param scope
     * @param status
     * @param photo
     * @return
     */
    public Article insertArticle(String title,String describe,String content,Long type,Integer scope,Integer status,
                                 String photo){
        Article article = new Article();
        article.setContent(content);
        article.setCreatetime(new Date());
        article.setDescribe(describe);
        article.setOpt1(0);
        article.setOpt2(0);
        article.setOpt3(0);
        article.setOpt4(0);
        article.setPhoto(photo);
        article.setScope(scope);
        article.setStatus(status);
        article.setTitle(title);
        article.setType(type);
        articleDao.insert(article);
        return article;
    }

    /**
     * 按浏览量查询
     * @return
     */
    public List<Article> queryRankByOpt3(){
        return articleDao.queryRankByOpt3();
    }

    public boolean deleteArticle(Long id){
        if(id != null){
            return articleDao.updateStatus(id,-1);
        }
        return false;
    }

    public boolean updateArticle(Long id,String title,String describe,String content,Long type,Integer scope,
                                 String photo){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("describe",describe);
        map.put("content",content);
        map.put("type",type);
        map.put("scope",scope);
        return articleDao.update(map);
    }

    public int getArticleCount(){
        return articleDao.getCount();
    }
}
