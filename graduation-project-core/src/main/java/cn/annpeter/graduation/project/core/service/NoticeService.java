package cn.annpeter.graduation.project.core.service;

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

    public List<Notice> getNoticeListByCourseId(Integer courseId){
        NoticeExample example = new NoticeExample();
        example.createCriteria()
                .andCourseIdEqualTo(courseId);
        return noticeMapper.selectByExample(example);
    }

    public void addNotice(Integer courseId, Integer type, String title, String content){
        Notice notice = new Notice();
        notice.setCourseId(courseId);
        notice.setType(type);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreateTime(new Date());
        noticeMapper.insert(notice);
    }
}