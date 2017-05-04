package cn.annpeter.graduation.project.dal.model;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    /**
     * INTEGER(10) 必填
     * 
     */
    private Integer id;

    /**
     * INTEGER(10)
     * 公告类型(0: 校内公告, 1:院内公告)
     */
    private Integer type;

    /**
     * INTEGER(10)
     * 所属课程id
     */
    private Integer courseId;

    /**
     * VARCHAR(256)
     * 标题
     */
    private String title;

    /**
     * VARCHAR(65535)
     * 内容
     */
    private String content;

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP]
     * 创建时间
     */
    private Date createTime;

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP]
     * 修改时间
     */
    private Date updateTime;

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
     * 获得 公告类型(0: 校内公告, 1:院内公告)
     */
    public Integer getType() {
        return type;
    }

    /**
     * INTEGER(10)
     * 设置 公告类型(0: 校内公告, 1:院内公告)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * INTEGER(10)
     * 获得 所属课程id
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * INTEGER(10)
     * 设置 所属课程id
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * VARCHAR(256)
     * 获得 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * VARCHAR(256)
     * 设置 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * VARCHAR(65535)
     * 获得 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * VARCHAR(65535)
     * 设置 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP]
     * 获得 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP]
     * 设置 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP]
     * 获得 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP]
     * 设置 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", courseId=").append(courseId);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
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
        Notice other = (Notice) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}