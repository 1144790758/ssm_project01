package com.lhl.dao;

import com.lhl.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @athor:lhl
 * @create:2020-02-03 16:58
 */
public interface IMemberDao {

    @Select("select * from member")
    Member findById(String memberId);
}
