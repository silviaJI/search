package com.im.service;

import com.im.dao.UserMapper;
import com.im.dao.model.User;
import com.im.dao.model.UserExample;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.im.daoEx.UserMapperEx;
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserMapperEx userMapperEx;

    private static final Logger logger = Logger.getLogger(UserService.class);

    public int countByExample(UserExample example) {
        return this.userMapper.countByExample(example);
    }

    public User selectByPrimaryKey(String id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    public List<User> selectByExample(UserExample example) {
        return this.userMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.userMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(User record) {
        return this.userMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(User record) {
        return this.userMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(UserExample example) {
        return this.userMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(User record, UserExample example) {
        return this.userMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(User record, UserExample example) {
        return this.userMapper.updateByExample(record, example);
    }

    public int insert(User record) {
        return this.userMapper.insert(record);
    }

    public int insertSelective(User record) {
        return this.userMapper.insertSelective(record);
    }
    
    public int updateSocre(User user){
    	return this.userMapperEx.updateSocre(user);
    }
}