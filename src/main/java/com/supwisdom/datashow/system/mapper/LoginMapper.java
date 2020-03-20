package com.supwisdom.datashow.system.mapper;

import com.supwisdom.datashow.system.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {

    public User login(@Param("loginName") String loginName, @Param("password") String password);

}
