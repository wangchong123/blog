package com.wangchong.blog.dao;

import com.wangchong.blog.entity.Pv;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PvDao {

    boolean insert(Pv pv);

    /**
     * 获取pv统计
     * @param date
     * @return
     */
    Pv getByDate(Date date);
}
