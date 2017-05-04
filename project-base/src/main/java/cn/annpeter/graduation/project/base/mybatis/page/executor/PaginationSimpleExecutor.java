package cn.annpeter.graduation.project.base.mybatis.page.executor;

import cn.annpeter.graduation.project.base.mybatis.page.DB_Type;
import cn.annpeter.graduation.project.base.mybatis.page.dialect.Dialect;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;


/**
 * Created on 2017/01/16
 *
 * @author annpeter.it@gmail.com
 */
public class PaginationSimpleExecutor extends SimpleExecutor implements PaginationBaseExecutor {
    private Dialect dialect;

    public PaginationSimpleExecutor(DB_Type dbType, Configuration configuration, Transaction transaction) {
        super(configuration, transaction);

        dialect = getDialect(dbType);
    }

    private <E> List<E> doSuperQuery(MappedStatement ms, Object parameter, RowBounds rowBounds,
                                     ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        return super.doQuery(ms, parameter, rowBounds, resultHandler, boundSql);
    }

    @Override
    public <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds,
                               ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        return doQueryList(dialect, transaction, this, ms, parameter, rowBounds, resultHandler, boundSql);
    }
}
