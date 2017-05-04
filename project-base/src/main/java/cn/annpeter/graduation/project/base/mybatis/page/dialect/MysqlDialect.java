package cn.annpeter.graduation.project.base.mybatis.page.dialect;


/**
 * Created on 2017/01/16
 *
 * @author annpeter.it@gmail.com
 */
public class MysqlDialect implements Dialect {

    @Override
    public String getCountSqlString(String sql) {
        if (sql != null && sql.length() > 0) {
            return "SELECT COUNT(*) AS " + RS_COUNT + " FROM (" + sql + ") T_TEMP ";
        }
        return null;
    }

    @Override
    public String getLimitString(String sql, int currPage, int pageSize) {
        if (sql != null && sql.length() > 0) {
            if (pageSize > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT * FROM (").append(sql).append(") T_TEMP");
                if (!sql.toUpperCase().contains("ORDER BY")) {
                    sb.append(" ORDER BY 1 ");
                }
                sb.append(" LIMIT ")
                        .append(currPage >= 0 ? currPage * pageSize : 0)
                        .append(", ")
                        .append(pageSize);
                return sb.toString();
            }
            return sql;
        }
        return null;
    }
}
