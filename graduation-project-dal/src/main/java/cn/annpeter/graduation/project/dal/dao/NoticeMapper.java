package cn.annpeter.graduation.project.dal.dao;

import cn.annpeter.graduation.project.base.mybatis.page.model.Page;
import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.dal.model.Notice;
import cn.annpeter.graduation.project.dal.model.NoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeMapper {
    long countByExample(NoticeExample example);

    int deleteByExample(NoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> selectByExample(NoticeExample example);

    Notice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    Page<Notice> selectPageByExample(@Param("example") NoticeExample example, PageRowBounds rowBounds);
}