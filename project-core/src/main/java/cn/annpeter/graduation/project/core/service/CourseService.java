package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.dal.dao.CourseMapper;
import cn.annpeter.graduation.project.dal.model.Course;
import cn.annpeter.graduation.project.dal.model.CourseExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static cn.annpeter.graduation.project.base.common.model.ResultCodeEnum.RESOURCE_NOT_FOUND;

/**
 * Created on 2017/04/15
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class CourseService {
    @Resource
    private CourseMapper courseMapper;

    public List<Course> getCourseList(Integer currPage, Integer pageSize) {
        CourseExample example = new CourseExample();
        example.createCriteria();
        example.setOrderByClause("seq DESC");
        return courseMapper.selectPageByExample(example, new PageRowBounds(currPage, pageSize));
    }

    public Course getCourseInfo(Integer courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    public Course getCourseInfo(String courseName) {
        CourseExample example = new CourseExample();
        example.createCriteria()
                .andNameEqualTo(courseName);
        List<Course> courseList = courseMapper.selectByExample(example);
        if (courseList.size() < 1) {
            throw new CommonException(RESOURCE_NOT_FOUND, "课程未找到");
        } else {
            return courseList.get(0);
        }
    }

    public void addOrUpdateCourse(Integer courseId, String name, String imgUrl, String intro) {

        Course course = new Course();
        course.setName(name);
        course.setImgUrl(imgUrl);
        course.setIntro(intro);
        course.setCreateTime(new Date());

        if (courseId != null) {
            course.setId(courseId);
            courseMapper.updateByPrimaryKeySelective(course);
        } else {
            courseMapper.insertSelective(course);
        }
    }

    public void delete(Integer courseId) {
        courseMapper.deleteByPrimaryKey(courseId);
    }
}
