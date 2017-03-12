package cn.annpeter.graduation.project.base.mybatis.page.dialect;


/**
 * Created on 2017/01/16
 *
 * @author annpeter.it@gmail.com
 */
public class OracleDialect implements Dialect {

    @Override
    public String getLimitString(String sql, int currPage, int pageSize) {
        if (sql != null && sql.length() > 0) {
            if (pageSize > 0) {
                return "SELECT * from ( SELECT T_TEMP.*, ROWNUM RN FROM (" + sql + ") T_TEMP" +
                        " WHERE ROWNUM <= " + (currPage >= 0 ? (currPage + 1) * pageSize : 0) +
                        " ) WHERE RN > " + (currPage >= 0 ? currPage * pageSize : 0);
            }
            return sql;
        }
        return null;
    }

    @Override
    public String getCountSqlString(String sql) {
        if (sql != null && sql.length() > 0) {
            return "SELECT COUNT(*) AS " + RS_COUNT + " FROM (" + sql + ") T_TEMP ";
        }
        return null;
    }
}
