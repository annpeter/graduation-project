package cn.annpeter.graduation.project.base.mybatis.page.dialect;


/**
 * Created on 2017/01/16
 *
 * @author annpeter.it@gmail.com
 */
public class DB2Dialect implements Dialect {

    private static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, int currPage, int pageSize) {
        sql = trim(sql);
        int startOfSelect = sql.toUpperCase().indexOf("SELECT");
        StringBuilder pagingSelect = new StringBuilder(sql.length() + 100)
                .append(sql.substring(0, startOfSelect)).append("SELECT * FROM ( SELECT ")
                .append(getRowNumber(sql));
        if (hasDistinct(sql)) {
            pagingSelect.append(" ROW_.* FROM ( ")
                    .append(sql.substring(startOfSelect))
                    .append(" ) as ROW_");
        } else {
            pagingSelect.append(sql.substring(startOfSelect + 6));
        }
        pagingSelect.append(" ) as TEMP_ WHERE ROW_NUMBER_ ");
        if (pageSize > 0) {
            pagingSelect.append("BETWEEN ")
                    .append(currPage >= 0 ? currPage * pageSize : 0)
                    .append(" AND ")
                    .append(currPage >= 0 ? (currPage + 1) * pageSize : 0);
        } else {
            pagingSelect.append("<= ").append(pageSize);
        }
        return pagingSelect.toString();
    }

    @Override
    public String getCountSqlString(String sql) {
        if (sql != null && sql.length() > 0) {
            return "SELECT COUNT(*) AS " + RS_COUNT + " FROM (" + sql + ") T_TEMP ";
        }
        return null;
    }

    private String trim(String sql) {
        sql = sql.trim();
        if (sql.endsWith(SQL_END_DELIMITER)) {
            sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
        }
        return sql;
    }

    private static boolean hasDistinct(String sql) {
        return sql.toUpperCase().contains("SELECT DISTINCT");
    }

    private String getRowNumber(String sql) {
        StringBuilder rowNumber = new StringBuilder(50).append("ROWNUMBER() OVER(");
        int orderByIndex = sql.toUpperCase().indexOf("ORDER BY");
        if ((orderByIndex > 0) && (!(hasDistinct(sql)))) {
            rowNumber.append(sql.substring(orderByIndex));
        }
        rowNumber.append(") as ROW_NUMBER_,");
        return rowNumber.toString();
    }
}
