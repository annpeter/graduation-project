package cn.annpeter.graduation.project.dal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * INTEGER(10) 必填
     * 
     */
    private Integer id;

    /**
     * VARCHAR(20) 必填
     * 用户名
     */
    private String name;

    /**
     * VARCHAR(50) 默认值[] 必填
     * 密码
     */
    @JsonIgnore
    private String pwd;

    /**
     * TINYINT(3) 默认值[0] 必填
     * 是否为管理员, 0否 1是
     */
    private Short isAdmin;

    /**
     * INTEGER(10)
     * 老师id
     */
    private Integer teacherId;

    /**
     * INTEGER(10) 必填
     * 所属课程
     */
    private Integer courseId;


    private String courseName;

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
     * VARCHAR(20) 必填
     * 获得 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * VARCHAR(20) 必填
     * 设置 用户名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * VARCHAR(50) 默认值[] 必填
     * 获得 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * VARCHAR(50) 默认值[] 必填
     * 设置 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * TINYINT(3) 默认值[0] 必填
     * 获得 是否为管理员, 0否 1是
     */
    public Short getIsAdmin() {
        return isAdmin;
    }

    /**
     * TINYINT(3) 默认值[0] 必填
     * 设置 是否为管理员, 0否 1是
     */
    public void setIsAdmin(Short isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * INTEGER(10)
     * 获得 老师id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * INTEGER(10)
     * 设置 老师id
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * INTEGER(10) 必填
     * 获得 所属课程
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * INTEGER(10) 必填
     * 设置 所属课程
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
        sb.append(", name=").append(name);
        sb.append(", pwd=").append(pwd);
        sb.append(", isAdmin=").append(isAdmin);
        sb.append(", teacherId=").append(teacherId);
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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()))
            && (this.getIsAdmin() == null ? other.getIsAdmin() == null : this.getIsAdmin().equals(other.getIsAdmin()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
        result = prime * result + ((getIsAdmin() == null) ? 0 : getIsAdmin().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}