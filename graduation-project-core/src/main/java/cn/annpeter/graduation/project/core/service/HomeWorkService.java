package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.dal.dao.HomeWorkCommitMapper;
import cn.annpeter.graduation.project.dal.dao.HomeWorkMapper;
import cn.annpeter.graduation.project.dal.model.HomeWork;
import cn.annpeter.graduation.project.dal.model.HomeWorkCommit;
import cn.annpeter.graduation.project.dal.model.HomeWorkExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static cn.annpeter.graduation.project.base.common.model.ResultCodeEnum.UNKNOWN_ERROR;

/**
 * Created on 2017/04/22
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class HomeWorkService {

    @Resource
    private HomeWorkMapper homeWorkMapper;
    @Resource
    private HomeWorkCommitMapper homeWorkCommitMapper;

    public List<HomeWork> getHomeWorkListByCourseId(Integer courseId) {
        HomeWorkExample example = new HomeWorkExample();
        example.createCriteria()
                .andStateEqualTo(1)
                .andCourseIdEqualTo(courseId);
        return homeWorkMapper.selectByExample(example);
    }

    public void commitHomeWork(Integer userId, String url) {
        HomeWorkCommit commit = new HomeWorkCommit();
        commit.setUserId(userId);
        commit.setUrl(url);
        int ret = homeWorkCommitMapper.insert(commit);
        if (ret < 1) {
            throw new CommonException(UNKNOWN_ERROR, "作业提交失败");
        }
    }

    public void addHomeWork(Integer courseId, String title, String url, Integer state) {
        HomeWork homeWork = new HomeWork();
        homeWork.setTitle(title);
        homeWork.setCourseId(courseId);
        homeWork.setState(state);
        homeWork.setUrl(url);
        homeWorkMapper.insertSelective(homeWork);
    }
}
