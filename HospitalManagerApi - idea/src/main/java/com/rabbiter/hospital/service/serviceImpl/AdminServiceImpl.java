package com.rabbiter.hospital.service.serviceImpl;

import com.rabbiter.hospital.mapper.AdminMapper;
import com.rabbiter.hospital.pojo.Admin;
import com.rabbiter.hospital.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

// AdminServiceImpl 类是 AdminService 接口的实现类
// 这个类中，定义了 login 方法的实现，该方法用于进行用户登录数据验证
@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * 登录数据校验
     * */
    // 用于用户登陆验证  用 selectById 方法基于 aId 从数据库中检索 Admin对象
    // 如果没有找到Admin对象，则返回 null
    // 如果找到了 Admin 对象，会继续检查 提供的 password 和存储的 管理员密码 是否匹配
    // 如果匹配，则返回 Admin对象
    // 如果不匹配，则返回 null
    @Override
    public Admin login(int aId, String aPassword){
        Admin admin = this.adminMapper.selectById(aId);
        if (admin == null) {
            return null;
        } else {
            if ((admin.getAPassword()).equals(aPassword)) {
                return admin;
            }
        }
        return null;
    }



}
