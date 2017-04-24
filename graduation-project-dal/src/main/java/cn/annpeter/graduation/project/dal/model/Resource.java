package cn.annpeter.graduation.project.dal.model;

import java.io.Serializable;
import java.util.Date;

public class Resource implements Serializable {
    /**
     * INTEGER(10) 必填
     * 
     */
    private Integer id;

    /**
     * VARCHAR(50)
     * 资源类型
     */
    private String type;

    /**
     * VARCHAR(100) 默认值[] 必填
     * 资源名称
     */
    private String name;

    /**
     * VARCHAR(11) 默认值[] 必填
     * 资源url
     */
    private String url;

    /**
     * INTEGER(10) 必填
     * 所属课程id
     */
    private Integer courseId;

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP] 必填
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
     * VARCHAR(50)
     * 获得 资源类型
     */
    public String getType() {
        return type;
    }

    /**
     * VARCHAR(50)
     * 设置 资源类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * VARCHAR(100) 默认值[] 必填
     * 获得 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * VARCHAR(100) 默认值[] 必填
     * 设置 资源名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * VARCHAR(11) 默认值[] 必填
     * 获得 资源url
     */
    public String getUrl() {
        return url;
    }

    /**
     * VARCHAR(11) 默认值[] 必填
     * 设置 资源url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * INTEGER(10) 必填
     * 获得 所属课程id
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * INTEGER(10) 必填
     * 设置 所属课程id
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP] 必填
     * 获得 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP] 必填
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
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", courseId=").append(courseId);
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
        Resource other = (Resource) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}