package cn.annpeter.graduation.project.core.service;


import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;
import cn.annpeter.graduation.project.base.common.util.EncryptUtils;
import cn.annpeter.graduation.project.core.config.GlobalConfig;
import cn.annpeter.graduation.project.dal.dao.UserMapper;
import cn.annpeter.graduation.project.dal.model.User;
import cn.annpeter.graduation.project.dal.model.UserExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static cn.annpeter.graduation.project.base.common.model.ResultCodeEnum.RESOURCE_NOT_FOUND;


/**
 * Created on 2017/03/04
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void delete(int id) {
        userMapper.deleteByPrimaryKey(id);
    }


    public int register(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(user.getName());
        List userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            throw new CommonException(ResultCodeEnum.RESOURCE_CONFLICT, "此用户名已被注册");
        }

        user.setPwd(EncryptUtils.MD5(user.getPwd() + GlobalConfig.userPwdSalt));
        return userMapper.insertSelective(user);
    }

    public User login(User user) {
        UserExample example = new UserExample();

        example.createCriteria()
                .andNameEqualTo(user.getName());

        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() == 0) {
            throw new CommonException(RESOURCE_NOT_FOUND, "用户不存在");
        } else {
            if (StringUtils.equals(EncryptUtils.MD5(user.getPwd() + GlobalConfig.userPwdSalt), userList.get(0).getPwd())) {

                User outUser = userList.get(0);
                if (outUser.getIsAdmin() != 2 && !Objects.equals(outUser.getCourseId(), user.getCourseId())) {
                    throw new CommonException(RESOURCE_NOT_FOUND, "您所登录的课程不正确");
                }
                return outUser;
            } else {
                throw new CommonException(ResultCodeEnum.FORBIDDEN, "密码错误");
            }
        }
    }

    public List<User> list() {
        return userMapper.selectAll();
    }

    public List<User> list(int courseId) {
        UserExample example = new UserExample();
        example.createCriteria().andCourseIdEqualTo(courseId)
                .andIsAdminEqualTo((short) 1);
        return userMapper.selectByExample(example);
    }

    public void updateUser(User user) {
        if (StringUtils.isNotEmpty(user.getPwd())) {
            user.setPwd(EncryptUtils.MD5(user.getPwd() + GlobalConfig.userPwdSalt));
        }
        userMapper.updateByPrimaryKeySelective(user);
    }
}
