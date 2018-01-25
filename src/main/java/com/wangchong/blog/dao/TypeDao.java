package com.wangchong.blog.dao;

import com.wangchong.blog.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TypeDao {

    boolean insert(Type obj);

    boolean delete(Long id);

    boolean update(Map<String,Object> map);

    Type getById(Long id);

    List<Type> queryList();

    List<Type> queryTypeNums();
}
