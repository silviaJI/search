package com.im.service;

import com.im.dao.AdminMapper;
import com.im.dao.model.Admin;
import com.im.dao.model.AdminExample;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    private static final Logger logger = Logger.getLogger(AdminService.class);

    public int countByExample(AdminExample example) {
        return this.adminMapper.countByExample(example);
    }

    public Admin selectByPrimaryKey(String id) {
        return this.adminMapper.selectByPrimaryKey(id);
    }

    public List<Admin> selectByExample(AdminExample example) {
        return this.adminMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.adminMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Admin record) {
        return this.adminMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Admin record) {
        return this.adminMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(AdminExample example) {
        return this.adminMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Admin record, AdminExample example) {
        return this.adminMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Admin record, AdminExample example) {
        return this.adminMapper.updateByExample(record, example);
    }

    public int insert(Admin record) {
        return this.adminMapper.insert(record);
    }

    public int insertSelective(Admin record) {
        return this.adminMapper.insertSelective(record);
    }
}