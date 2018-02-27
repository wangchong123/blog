package com.wangchong.blog.dao;

import com.wangchong.blog.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 分类
 */
@Repository
public interface TypeDao {

    boolean insert(Type obj);

    boolean delete(Long id);

    boolean update(Map<String,Object> map);

    Type getById(Long id);

    /**
     * 查询全部
     * @return
     */
    List<Type> queryList();

    /**
     * 查询分类并统计文章数量
     * @return
     */
    List<Type> queryTypeNums();
}
