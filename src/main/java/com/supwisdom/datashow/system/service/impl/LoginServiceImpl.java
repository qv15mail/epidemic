package com.supwisdom.datashow.system.service.impl;

import com.supwisdom.datashow.system.domain.User;
import com.supwisdom.datashow.system.mapper.LoginMapper;
import com.supwisdom.datashow.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public User login(String loginName, String password) {
        return loginMapper.login(loginName,password);
    }
}
