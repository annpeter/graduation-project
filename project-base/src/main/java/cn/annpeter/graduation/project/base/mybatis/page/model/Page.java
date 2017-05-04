package cn.annpeter.graduation.project.base.mybatis.page.model;

import java.util.ArrayList;

/**
 * Created on 2017/01/16
 *
 * @author annpeter.it@gmail.com
 */
public class Page<E> extends ArrayList<E> {
    private static int DEFAULT_COUNT = 20;

    private int pageSize = DEFAULT_COUNT;   // 每页大小
    private int currPageNo = 1;             // 当前页码
    private int totalPage = 1;              // 总页数
    private long recordAmount;              // 总记录条数

    public Page() {
    }

    public Page(PageRowBounds pageRowBounds){
        this.pageSize = pageRowBounds.getPageSize();
        this.currPageNo = pageRowBounds.getCurrPageNo();
    }

    public Page(int pageSize, int currPageNo) {
        this.pageSize = pageSize;
        this.currPageNo = currPageNo;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public void setCurrPageNo(int currPageNo) {
        if (currPageNo < 1) {
            this.currPageNo = 1;
        }
        this.currPageNo = currPageNo;
    }

    public void setTotalPage(long recordNumber) {
        if (recordNumber > 0) {
            long totalPage = recordNumber / this.pageSize;
            this.totalPage = (int) (recordNumber % this.pageSize != 0 ? ++totalPage : totalPage);
            this.recordAmount = recordNumber;
        }
    }

    public int getCurrPageNo() {
        return this.currPageNo > this.totalPage ? this.totalPage : this.currPageNo;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public long getRecordAmount() {
        return this.recordAmount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPrePageNo() {
        return this.currPageNo > 2 ? this.currPageNo - 1 : 1;
    }

    public int getNextPageNo() {
        return this.currPageNo < this.totalPage ? this.currPageNo + 1 : this.totalPage;
    }

    public int getStartIndex() {
        return (this.currPageNo - 1) * this.pageSize + 1;
    }


    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", currPageNo=" + currPageNo +
                ", totalPage=" + totalPage +
                ", recordAmount=" + recordAmount +
                ", records=" + super.toString() +
                '}';
    }
}
