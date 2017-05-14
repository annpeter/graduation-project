package cn.annpeter.graduation.project.core.service.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.web;

/**
 * Created on 2017/03/28
 *
 * @author annpeter.it@gmail.com
 */
public class ApplicationExecutorHolder {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationExecutorHolder.class);

    private static final ScheduledExecutorService monitorThread = Executors.newSingleThreadScheduledExecutor();
    private static final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);

    static {
        monitorThread.scheduleAtFixedRate(
                () -> logger.debug("线程池状态: Active={}, InQueue={}.", threadPool.getActiveCount(), threadPool.getQueue().size())
                , 0, 30, TimeUnit.SECONDS);
    }

    public static void execute(String title, Runnable runnable) {
        String logRequestId = MDC.get(web.logRequestId);
        String requestId = StringUtils.isEmpty(logRequestId) ? "NEW-" + RandomStringUtils.randomAlphabetic(web.logRequestIdLength - 4) : logRequestId;

        threadPool.execute(() -> {
            MDC.put(web.logRequestId, requestId);

            long startTime = System.currentTimeMillis();
            logger.info("{}====开始执行. 线程池状态: Active={}, InQueue={}.", title, threadPool.getActiveCount(), threadPool.getQueue().size());

            try {
                runnable.run();
            } catch (Throwable ex) {
                logger.error("{}====执行出错. 线程池状态: Active={}, InQueue={}.", title, threadPool.getActiveCount(), threadPool.getQueue().size(), ex);
            } finally {
                logger.info("{}====结束执行, 异步执行耗时{}ms. 线程池状态: Active={}, InQueue={}.", title, System.currentTimeMillis() - startTime,
                        threadPool.getActiveCount(), threadPool.getQueue().size());
                MDC.clear();
            }
        });
    }
}
