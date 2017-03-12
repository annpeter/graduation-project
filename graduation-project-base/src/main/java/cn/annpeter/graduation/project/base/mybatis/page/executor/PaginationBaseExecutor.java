package cn.annpeter.graduation.project.base.mybatis.page.executor;

import cn.annpeter.graduation.project.base.mybatis.page.dialect.*;
import cn.annpeter.graduation.project.base.mybatis.page.exception.UnSupportedDBTypeException;
import cn.annpeter.graduation.project.base.mybatis.page.model.PageRowBounds;
import cn.annpeter.graduation.project.base.mybatis.page.DB_Type;
import cn.annpeter.graduation.project.base.mybatis.page.exception.DBTypeCanNotBeNullException;
import cn.annpeter.graduation.project.base.mybatis.page.model.Page;
import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created on 2017/02/17
 *
 * @author annpeter.it@gmail.com
 */
interface PaginationBaseExecutor {

    default Dialect getDialect(DB_Type dbType) {
        if (dbType != null) {
            if (dbType == DB_Type.MYSQL) {
                return new MysqlDialect();
            } else if (dbType == DB_Type.ORACLE) {
                return new OracleDialect();
            } else if (dbType == DB_Type.DB2) {
                return new DB2Dialect();
            } else if (dbType == DB_Type.SQL_SERVER) {
                return new SqlServerDialect();
            } else {
                throw new UnSupportedDBTypeException(dbType.name());
            }
        } else {
            throw new DBTypeCanNotBeNullException();
        }
    }

    @SuppressWarnings("unchecked")
    default <E> List<E> doQueryList(Dialect dialect, Transaction transaction, BaseExecutor executor,
                                    MappedStatement ms, Object parameter, RowBounds rowBounds,
                                    ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        String sourceSql = boundSql.getSql();

        if (rowBounds instanceof PageRowBounds) {
            PageRowBounds pageRowBounds = (PageRowBounds) rowBounds;
            Page page = new Page(pageRowBounds);

            List list = queryRecordList(executor, dialect, ms, parameter, pageRowBounds, resultHandler, boundSql);
            page.addAll(list);

            long recordNumber = queryRecordNumber(page, dialect, transaction, sourceSql, ms, parameter, rowBounds, boundSql);
            page.setTotalPage(recordNumber);

            return page;
        } else {
            Method doSuperQueryMethod = ReflectionUtils.findMethod(executor.getClass(), "doSuperQuery",
                    MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, BoundSql.class);
            ReflectionUtils.makeAccessible(doSuperQueryMethod);
            return (List) ReflectionUtils.invokeMethod(doSuperQueryMethod, executor, ms, parameter,
                    rowBounds, resultHandler, boundSql);
        }
    }

    @SuppressWarnings("unchecked")
    default <E> List<E> queryRecordList(BaseExecutor executor, Dialect dialect, MappedStatement ms, Object parameter,
                                        PageRowBounds pageRowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        if (dialect != null && pageRowBounds != null) {
            String sql = dialect.getLimitString(boundSql.getSql(), pageRowBounds.getCurrPageNo(), pageRowBounds.getPageSize());

            Field field = ReflectionUtils.findField(BoundSql.class, "sql");
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, boundSql, sql);
        }

        Method doSuperQueryMethod = ReflectionUtils.findMethod(executor.getClass(), "doSuperQuery",
                MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, BoundSql.class);
        ReflectionUtils.makeAccessible(doSuperQueryMethod);
        return (List<E>) ReflectionUtils.invokeMethod(doSuperQueryMethod, executor, ms, parameter,
                pageRowBounds, resultHandler, boundSql);
    }

    default long queryRecordNumber(Page page, Dialect dialect, Transaction transaction, String sourceSql, MappedStatement ms,
                                   Object parameter, RowBounds rowBounds, BoundSql boundSql) throws SQLException {

        if (page.size() < page.getPageSize()) {
            page.setTotalPage(page.size());
        } else {
            if (dialect != null) {
                if (rowBounds.getLimit() > 0) {
                    String countSql = dialect.getCountSqlString(sourceSql);

                    Connection conn = transaction.getConnection();
                    try (PreparedStatement stmt = conn.prepareStatement(countSql)) {

                        DefaultParameterHandler handler = new DefaultParameterHandler(ms, parameter, boundSql);
                        handler.setParameters(stmt);

                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            return rs.getLong(1);
                        }
                        rs.close();
                    }
                }
            }
        }
        return 0;
    }
}
