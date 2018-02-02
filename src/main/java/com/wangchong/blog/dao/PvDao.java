package com.wangchong.blog.dao;

import com.wangchong.blog.entity.Pv;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PvDao {

    boolean insert(Pv pv);

    Pv getByDate(Date date);
}
