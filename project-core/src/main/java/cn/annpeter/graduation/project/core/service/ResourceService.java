package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.dal.dao.ResourceMapper;
import cn.annpeter.graduation.project.dal.model.Resource;
import cn.annpeter.graduation.project.dal.model.ResourceExample;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/04/22
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    public List<Resource> getResourceList(Integer currPage, Integer pageSize) {
        ResourceExample example = new ResourceExample();
        example.createCriteria();
        example.setOrderByClause(" update_time DESC  ");
        return resourceMapper.selectPageByExample(example, new PageRowBounds(currPage, pageSize));
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

}
