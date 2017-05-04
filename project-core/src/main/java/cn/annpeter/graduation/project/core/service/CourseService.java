package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.dal.dao.CourseMapper;
import cn.annpeter.graduation.project.dal.model.Course;
import cn.annpeter.graduation.project.dal.model.CourseExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    public void addCourse(String name, String imgUrl, String intro) {
        Course course = new Course();
        course.setName(name);
        course.setImgUrl(imgUrl);
        course.setIntro(intro);
        course.setCreateTime(new Date());
        courseMapper.insertSelective(course);
    }

}
