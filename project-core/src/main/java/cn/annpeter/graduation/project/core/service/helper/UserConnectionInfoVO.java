package cn.annpeter.graduation.project.core.service.helper;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/03/23
 *
 * @author annpeter.it@gmail.com
 */
public class UserConnectionInfoVO {
    private ConnectionInfoItem total;
    private ConnectionInfoItem today;
    private List<ConnectionInfoItem> days;


    public static class ConnectionInfoItem {
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date date;
        private Integer view = 0;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public int getView() {
            return view;
        }

        public void setView(Integer view) {
            this.view = view;
        }
    }

    public ConnectionInfoItem getTotal() {
        return total;
    }

    public void setTotal(ConnectionInfoItem total) {
        this.total = total;
    }

    public ConnectionInfoItem getToday() {
        return today;
    }

    public void setToday(ConnectionInfoItem today) {
        this.today = today;
    }

    public List<ConnectionInfoItem> getDays() {
        return days;
    }

    public void setDays(List<ConnectionInfoItem> days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "UserConnectionInfoVO{" +
                "total=" + total +
                ", today=" + today +
                ", days=" + days +
                '}';
    }
}
