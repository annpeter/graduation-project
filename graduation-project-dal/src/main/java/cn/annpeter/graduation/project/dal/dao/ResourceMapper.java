package cn.annpeter.graduation.project.dal.dao;

import cn.annpeter.graduation.project.base.mybatis.page.model.Page;
import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.dal.model.Resource;
import cn.annpeter.graduation.project.dal.model.ResourceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceMapper {
    long countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    Page<Resource> selectPageByExample(@Param("example") ResourceExample example, PageRowBounds rowBounds);
}