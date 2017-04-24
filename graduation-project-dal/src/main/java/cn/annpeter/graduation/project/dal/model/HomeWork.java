package cn.annpeter.graduation.project.dal.model;

import java.io.Serializable;

public class HomeWork implements Serializable {
    /**
     * INTEGER(10) 必填
     * 
     */
    private Integer id;

    /**
     * INTEGER(10)
     * 课程id
     */
    private Integer courseId;

    /**
     * VARCHAR(256)
     * 作业标题
     */
    private String title;

    /**
     * VARCHAR(256)
     * 文件url
     */
    private String url;

    /**
     * INTEGER(10) 必填
     * 1表示可用, 0表示不可用
     */
    private Integer state;

    private static final long serialVersionUID = 1L;

    /**
     * INTEGER(10) 必填
     * 获得 
     */
    public Integer getId() {
        return id;
    }

    /**
     * INTEGER(10) 必填
     * 设置 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * INTEGER(10)
     * 获得 课程id
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * INTEGER(10)
     * 设置 课程id
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * VARCHAR(256)
     * 获得 作业标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * VARCHAR(256)
     * 设置 作业标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * VARCHAR(256)
     * 获得 文件url
     */
    public String getUrl() {
        return url;
    }

    /**
     * VARCHAR(256)
     * 设置 文件url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * INTEGER(10) 必填
     * 获得 1表示可用, 0表示不可用
     */
    public Integer getState() {
        return state;
    }

    /**
     * INTEGER(10) 必填
     * 设置 1表示可用, 0表示不可用
     */
    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", title=").append(title);
        sb.append(", url=").append(url);
        sb.append(", state=").append(state);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        HomeWork other = (HomeWork) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }
}