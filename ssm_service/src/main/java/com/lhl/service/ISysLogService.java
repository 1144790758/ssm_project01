package com.lhl.service;

import com.lhl.domain.SysLog;

import java.util.List;

/**
 * @athor:lhl
 * @create:2020-02-11 18:49
 */
public interface ISysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll(Integer pageNum,Integer size);
}
