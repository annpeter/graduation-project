package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.core.common.exception.ComExp;
import cn.annpeter.graduation.project.core.common.exception.ComResEnum;
import cn.annpeter.graduation.project.dal.dao.UserMapper;
import cn.annpeter.graduation.project.dal.model.User;
import cn.annpeter.graduation.project.dal.model.UserExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created on 2017/03/04
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public int register(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(user.getName());
        List userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            throw new ComExp(ComResEnum.RESOURCE_CONFLICT, "此用户名已被注册");
        }

        return userMapper.insert(user);
    }

    public User login(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(user.getName());
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() == 0) {
            throw new ComExp(ComResEnum.RESOURCE_NOT_FOUND, "用户不存在");
        } else {
            if (StringUtils.equals(user.getPwd(), userList.get(0).getPwd())) {
                return userList.get(0);
            } else {
                throw new ComExp(ComResEnum.FORBIDDEN, "密码错误");
            }
        }
    }

}
