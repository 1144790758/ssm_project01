package com.lhl.service.impl;

import com.github.pagehelper.PageHelper;
import com.lhl.dao.ISysLogDao;
import com.lhl.domain.SysLog;
import com.lhl.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-11 18:50
 */
@Service
@Transactional
public class SysLogService implements ISysLogService {

    @Autowired
    ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        return sysLogDao.findAll();
    }
}
