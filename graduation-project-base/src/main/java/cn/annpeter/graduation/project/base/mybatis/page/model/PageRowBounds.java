package cn.annpeter.graduation.project.base.mybatis.page.model;

import org.apache.ibatis.session.RowBounds;

/**
 * Created on 2017/02/18
 *
 * @author annpeter.it@gmail.com
 */
public class PageRowBounds extends RowBounds {
    private static int DEFAULT_COUNT = 20;

    private int currPageNo = 0;             // 当前页码, 0为首页
    private int pageSize = DEFAULT_COUNT;   // 每页大小

    public PageRowBounds() {
        super();
    }

    public PageRowBounds(int currPageNo, int pageSize) {
        this.currPageNo = currPageNo;
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrPageNo() {
        return currPageNo;
    }
}
