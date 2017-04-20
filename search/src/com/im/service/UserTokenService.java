package com.im.service;

import com.im.dao.UserTokenMapper;
import com.im.dao.model.UserToken;
import com.im.dao.model.UserTokenExample;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {
    @Autowired
    private UserTokenMapper userTokenMapper;

    private static final Logger logger = Logger.getLogger(UserTokenService.class);

    public int countByExample(UserTokenExample example) {
        return this.userTokenMapper.countByExample(example);
    }

    public UserToken selectByPrimaryKey(String id) {
        return this.userTokenMapper.selectByPrimaryKey(id);
    }

    public List<UserToken> selectByExample(UserTokenExample example) {
        return this.userTokenMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.userTokenMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserToken record) {
        return this.userTokenMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserToken record) {
        return this.userTokenMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(UserTokenExample example) {
        return this.userTokenMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(UserToken record, UserTokenExample example) {
        return this.userTokenMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(UserToken record, UserTokenExample example) {
        return this.userTokenMapper.updateByExample(record, example);
    }

    public int insert(UserToken record) {
        return this.userTokenMapper.insert(record);
    }

    public int insertSelective(UserToken record) {
        return this.userTokenMapper.insertSelective(record);
    }
}