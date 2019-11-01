package com.txf.service;

import com.txf.entity.User;
import com.txf.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2019/11/1.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User findById(String id){
        return userMapper.findById(id);
    }

    public int addUser(String name,String age){
        return userMapper.addUser(name,age);
    }

    public void updataById(String id,String name){
        userMapper.updataById(id,name);
    }

    public void deleteById(String id){
        userMapper.deleteById(id);
    }
}
