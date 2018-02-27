package com.wangchong.blog.service;

import com.wangchong.blog.dao.TypeDao;
import com.wangchong.blog.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeService {

    @Autowired
    private TypeDao typeDao;

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public Type getType(Long id){
        if(id != null){
            return typeDao.getById(id);
        }
        return null;
    }

    /**
     * 新增
     * @param name
     * @param status
     * @param scope
     * @return
     */
    public boolean createType(String name,Integer status,Integer scope){
        Type obj = new Type();
        obj.setName(name);
        obj.setStatus(status);
        obj.setScope(scope);
        return typeDao.insert(obj);
    }

    /**
     * 更新
     * @param name
     * @param status
     * @param scope
     * @return
     */
    public boolean updateType(String name,Integer status,Integer scope){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("status",status);
        map.put("scope",scope);
        return typeDao.update(map);
    }

    public boolean deleteType(Long id){
        if(id!=null){
            return typeDao.delete(id);
        }
        return false;
    }

    /**
     * 查询带文章数量
     * @return
     */
    public List<Type> queryTypeList(){
        return typeDao.queryTypeNums();
    }

    /**
     * 查询
     * @return
     */
    public List<Type> listType(){
        return typeDao.queryList();
    }


}
