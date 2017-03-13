package cn.annpeter.graduation.project.core.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created on 2017/03/13
 *
 * @author annpeter.it@gmail.com
 */
@DisallowConcurrentExecution
public class TestQuartzJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(TestQuartzJob.class);


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        try {
            ApplicationContext applicationContext = getApplicationContext(context);

            Object userMapper = applicationContext.getBean("userMapper");
            System.out.println("userMapper = " + userMapper);
            System.out.println("applicationContext = " + applicationContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("执行定时任务==={}.", context.getJobInstance());
    }


    private static final String APPLICATION_CONTEXT_KEY = "applicationContextKey";
    private ApplicationContext getApplicationContext(JobExecutionContext context) throws Exception {
        ApplicationContext appCtx = null;
        appCtx = (ApplicationContext) context.getScheduler().getContext().get(APPLICATION_CONTEXT_KEY);
        if (appCtx == null) {
            throw new JobExecutionException("No application context available in scheduler context for key \"" + APPLICATION_CONTEXT_KEY + "\"");
        }
        return appCtx;
    }
}
