package cn.annpeter.graduation.project.base.mybatis.page.dialect;


/**
 * Created on 2017/01/16
 *
 * @author annpeter.it@gmail.com
 */
public class SqlServerDialect implements Dialect {

    private static final String SELECT = "SELECT ";
    private static final String FROM = "FROM ";
    private static final String DISTINCT = "DISTINCT ";

    @Override
    public String getLimitString(String sql, int currPage, int pageSize) {
        if (sql != null && sql.length() > 0) {
            if (pageSize > 0) {
                StringBuilder sb = new StringBuilder(sql.trim().toUpperCase());

                int orderByIndex = sb.indexOf("ORDER BY");
                CharSequence orderBy = orderByIndex > 0 ? sb.subSequence(orderByIndex, sb.length())
                        : "ORDER BY CURRENT_TIMESTAMP";

                if (orderByIndex > 0) {
                    sb.delete(orderByIndex, orderByIndex + orderBy.length());
                }

                replaceDistinctWithGroupBy(sb);

                insertRowNumberFunction(sb, orderBy);

                sb.insert(0, "WITH QUERY AS (").append(") SELECT * FROM QUERY ");
                sb.append("WHERE T_TEMP BETWEEN ")
                        .append(currPage >= 0 ? currPage * pageSize : 0)
                        .append(" AND ")
                        .append(currPage >= 0 ? (currPage + 1) * pageSize : 0);

                return sb.toString();
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

    private void replaceDistinctWithGroupBy(StringBuilder sql) {
        int distinctIndex = sql.indexOf(SqlServerDialect.DISTINCT);
        if (distinctIndex > 0) {
            sql.delete(distinctIndex, distinctIndex + SqlServerDialect.DISTINCT.length() + 1);
            sql.append(" GROUP BY").append(getSelectFieldsWithoutAliases(sql));
        }
    }

    private CharSequence getSelectFieldsWithoutAliases(StringBuilder sql) {
        String select = sql.substring(sql.indexOf(SqlServerDialect.SELECT) + SqlServerDialect.SELECT.length(),
                sql.indexOf(SqlServerDialect.FROM));
        return stripAliases(select);
    }

    private String stripAliases(String str) {
        return str.replaceAll("\\sas[^,]+(,?)", "$1");
    }

    private void insertRowNumberFunction(StringBuilder sql, CharSequence orderBy) {
        int selectEndIndex = sql.indexOf(SqlServerDialect.SELECT) + SqlServerDialect.SELECT.length();
        sql.insert(selectEndIndex, " ROW_NUMBER() OVER (" + orderBy + ") as T_TEMP,");
    }

}
