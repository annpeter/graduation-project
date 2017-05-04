package cn.annpeter.graduation.project.dal.dao;

import cn.annpeter.graduation.project.base.mybatis.page.model.Page;
import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.dal.model.HomeWorkCommit;
import cn.annpeter.graduation.project.dal.model.HomeWorkCommitExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeWorkCommitMapper {
    long countByExample(HomeWorkCommitExample example);

    int deleteByExample(HomeWorkCommitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeWorkCommit record);

    int insertSelective(HomeWorkCommit record);

    List<HomeWorkCommit> selectByExample(HomeWorkCommitExample example);

    HomeWorkCommit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomeWorkCommit record, @Param("example") HomeWorkCommitExample example);

    int updateByExample(@Param("record") HomeWorkCommit record, @Param("example") HomeWorkCommitExample example);

    int updateByPrimaryKeySelective(HomeWorkCommit record);

    int updateByPrimaryKey(HomeWorkCommit record);

    Page<HomeWorkCommit> selectPageByExample(@Param("example") HomeWorkCommitExample example, PageRowBounds rowBounds);
}