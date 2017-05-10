package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.util.PageUtils;
import cn.annpeter.graduation.project.base.mybatis.page.model.Page;
import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.dal.dao.HomeWorkCommitMapper;
import cn.annpeter.graduation.project.dal.dao.HomeWorkMapper;
import cn.annpeter.graduation.project.dal.model.HomeWork;
import cn.annpeter.graduation.project.dal.model.HomeWorkCommit;
import cn.annpeter.graduation.project.dal.model.HomeWorkExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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

    public Map getHomeWorkListByCourseId(Integer currPage, Integer pageSize, Integer courseId) {
        HomeWorkExample example = new HomeWorkExample();
        example.createCriteria()
                .andStateEqualTo(1)
                .andCourseIdEqualTo(courseId);

        example.setOrderByClause(" update_time DESC  ");

        Page resourcePage = homeWorkMapper.selectPageByExample(example, new PageRowBounds(currPage, pageSize));
        return PageUtils.getPageInfo(resourcePage);
    }

    public void commitHomeWork(Integer userId, Integer homeWorkId, String url) {
        HomeWorkCommit commit = new HomeWorkCommit();
        commit.setUserId(userId);
        commit.setUrl(url);
        commit.setHomeWorkId(homeWorkId);
        int ret = homeWorkCommitMapper.insert(commit);
        if (ret < 1) {
            throw new CommonException(UNKNOWN_ERROR, "作业提交失败");
        }
    }

    public void addHomeWork(Integer courseId, String title, String url, Integer userId) {
        HomeWork homeWork = new HomeWork();
        homeWork.setTitle(title);
        homeWork.setCourseId(courseId);
        homeWork.setUrl(url);
        homeWork.setUserId(userId);
        homeWorkMapper.insertSelective(homeWork);
    }

    public void checkHomeWorkCommit(Integer homeWorkCommitId, Float score, String comment) {
        HomeWorkCommit commit = new HomeWorkCommit();
        commit.setId(homeWorkCommitId);
        commit.setScore(score);
        commit.setComment(comment);
        homeWorkCommitMapper.updateByPrimaryKeySelective(commit);
    }
}
