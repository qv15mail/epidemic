package com.supwisdom.datashow.system.service;

import com.supwisdom.datashow.system.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    public User login(String loginName, String password);
}
