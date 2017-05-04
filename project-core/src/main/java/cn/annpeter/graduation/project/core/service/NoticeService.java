package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.mybatis.page.model.Page;
import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.dal.dao.NoticeMapper;
import cn.annpeter.graduation.project.dal.model.Notice;
import cn.annpeter.graduation.project.dal.model.NoticeExample;
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
public class NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    public Page<Notice> getNoticeListByCourseId(Integer courseId, Integer type){
        NoticeExample example = new NoticeExample();
        example.createCriteria()
                .andCourseIdEqualTo(courseId)
                .andTypeEqualTo(type);
        example.setOrderByClause(" update_time DESC ");

        return noticeMapper.selectPageByExample(example, new PageRowBounds(0, 5));
    }

    public void addNotice(Integer courseId, Integer type, String title, String content){
        Notice notice = new Notice();
        notice.setCourseId(courseId);
        notice.setType(type);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreateTime(new Date());
        noticeMapper.insertSelective(notice);
    }
}
