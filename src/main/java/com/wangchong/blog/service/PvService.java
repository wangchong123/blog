package com.wangchong.blog.service;

import com.wangchong.blog.dao.PvDao;
import com.wangchong.blog.entity.Pv;
import com.wangchong.blog.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class PvService {

    @Autowired
    private PvDao pvDao;

    public boolean insert(HttpServletRequest request){
        String ip = CommonUtil.getIpAddr(request);
        Pv pv = new Pv();
        pv.setCreatetime(new Date());
        pv.setIp(ip);
        return pvDao.insert(pv);
    }

    /**
     * 获取pv统计
     * @param date
     * @return
     */
    public Pv getByDate(Date date){
        if(date == null){
            date = new Date();
        }
        return pvDao.getByDate(date);
    }
}
