package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.common.util.PageUtils;
import cn.annpeter.graduation.project.base.mybatis.page.model.Page;
import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.dal.dao.ResourceMapper;
import cn.annpeter.graduation.project.dal.model.Resource;
import cn.annpeter.graduation.project.dal.model.ResourceExample;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created on 2017/04/22
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    public Map getResourceList(Integer currPage, Integer pageSize, Integer courseId) {
        ResourceExample example = new ResourceExample();
        example.createCriteria()
                .andCourseIdEqualTo(courseId);

        example.setOrderByClause(" update_time DESC  ");
        Page resourcePage = resourceMapper.selectPageByExample(example, new PageRowBounds(currPage, pageSize));

        return PageUtils.getPageInfo(resourcePage);
    }

    public void addResource(String type, String name, String url, Integer courseId) {
        Resource resource = new Resource();
        resource.setUrl(url);
        resource.setType(type);
        resource.setName(name);
        resource.setCourseId(courseId);
        resource.setCreateTime(new Date());
        resourceMapper.insertSelective(resource);
    }

    public void deleteResource(Integer resourceId) {
        resourceMapper.deleteByPrimaryKey(resourceId);
    }

}
