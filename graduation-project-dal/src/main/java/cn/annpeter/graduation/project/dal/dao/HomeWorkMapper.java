package cn.annpeter.graduation.project.dal.dao;

import cn.annpeter.graduation.project.base.mybatis.page.model.Page;
import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.dal.model.HomeWork;
import cn.annpeter.graduation.project.dal.model.HomeWorkExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeWorkMapper {
    long countByExample(HomeWorkExample example);

    int deleteByExample(HomeWorkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeWork record);

    int insertSelective(HomeWork record);

    List<HomeWork> selectByExample(HomeWorkExample example);

    HomeWork selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomeWork record, @Param("example") HomeWorkExample example);

    int updateByExample(@Param("record") HomeWork record, @Param("example") HomeWorkExample example);

    int updateByPrimaryKeySelective(HomeWork record);

    int updateByPrimaryKey(HomeWork record);

    Page<HomeWork> selectPageByExample(@Param("example") HomeWorkExample example, PageRowBounds rowBounds);
}