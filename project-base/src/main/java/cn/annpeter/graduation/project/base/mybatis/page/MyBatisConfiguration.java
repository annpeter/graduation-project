package cn.annpeter.graduation.project.base.mybatis.page;

import cn.annpeter.graduation.project.base.mybatis.page.executor.PaginationReuseExecutor;
import cn.annpeter.graduation.project.base.mybatis.page.executor.PaginationSimpleExecutor;
import cn.annpeter.graduation.project.base.mybatis.page.executor.PaginationBatchExecutor;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.transaction.Transaction;


/**
 * Created on 2017/01/17
 *
 * @author annpeter.it@gmail.com
 */
public class MyBatisConfiguration extends Configuration {

    private DB_Type dbType;

    public void setDbType(DB_Type dbType) {
        this.dbType = dbType;
    }

    @Override
    public Executor newExecutor(Transaction transaction, ExecutorType executorType) {

        executorType = executorType == null ? ExecutorType.SIMPLE : executorType;

        Executor executor;
        if (ExecutorType.BATCH == executorType) {
            executor = new PaginationBatchExecutor(dbType, this, transaction);
        } else if (ExecutorType.REUSE == executorType) {
            executor = new PaginationReuseExecutor(dbType, this, transaction);
        } else {
            executor = new PaginationSimpleExecutor(dbType, this, transaction);
        }

        if (cacheEnabled) {
            executor = new CachingExecutor(executor);
        }

        executor = (Executor) interceptorChain.pluginAll(executor);
        return executor;
    }

}
