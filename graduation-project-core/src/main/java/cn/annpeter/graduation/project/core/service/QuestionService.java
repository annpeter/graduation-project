package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.dal.dao.QuestionMapper;
import cn.annpeter.graduation.project.dal.model.Question;
import cn.annpeter.graduation.project.dal.model.QuestionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static cn.annpeter.graduation.project.base.common.model.ResultCodeEnum.UNKNOWN_ERROR;

/**
 * Created on 2017/04/23
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    public void insertQuestion(String content, Integer courseId) {
        Question question = new Question();
        question.setContent(content);
        question.setCourseId(courseId);
        question.setCreateTime(new Date());
        question.setUpdateTime(new Date());
        int ret = questionMapper.insert(question);
        if (ret < 1) {
            throw new CommonException(UNKNOWN_ERROR, "添加问题异常");
        }
    }

    public List<Question> list(Integer courseId) {
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCourseIdEqualTo(courseId);

        return questionMapper.selectByExample(example);
    }

    public void updateAnswer(String answer, Integer questionId) {
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andIdEqualTo(questionId);

        Question question = new Question();
        question.setAnswer(answer);
        questionMapper.updateByExampleSelective(question, example);
    }
}