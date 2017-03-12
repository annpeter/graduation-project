package cn.annpeter.graduation.project.base.mybatis.page.dialect;

/**
 * Created on 2017/01/16
 *
 * @author annpeter.it@gmail.com
 */
public interface Dialect {
    String RS_COUNT = "RS_COUNT";

    /**
     * 分页语句的数据库方言
     */

    /**
     *
     * @param currPage 当前页码, 0为首页
     */
    String getLimitString(String sql, int currPage, int pageSize);

    /**
     * count语句的数据库方言
     */
    String getCountSqlString(String sql);

}
