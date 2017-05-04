package cn.annpeter.graduation.project.dal.model;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable {
    /**
     * INTEGER(10) 必填
     * 
     */
    private Integer id;

    /**
     * VARCHAR(20) 默认值[] 必填
     * 课程名称
     */
    private String name;

    /**
     * INTEGER(10) 必填
     * 课程排序
     */
    private Integer seq;

    /**
     * VARCHAR(256)
     * 课程logo
     */
    private String imgUrl;

    /**
     * VARCHAR(65535)
     * 课程简介
     */
    private String intro;

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
     * VARCHAR(20) 默认值[] 必填
     * 获得 课程名称
     */
    public String getName() {
        return name;
    }

    /**
     * VARCHAR(20) 默认值[] 必填
     * 设置 课程名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * INTEGER(10) 必填
     * 获得 课程排序
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * INTEGER(10) 必填
     * 设置 课程排序
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * VARCHAR(256)
     * 获得 课程logo
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * VARCHAR(256)
     * 设置 课程logo
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * VARCHAR(65535)
     * 获得 课程简介
     */
    public String getIntro() {
        return intro;
    }

    /**
     * VARCHAR(65535)
     * 设置 课程简介
     */
    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
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
        sb.append(", seq=").append(seq);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", intro=").append(intro);
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
        Course other = (Course) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSeq() == null ? other.getSeq() == null : this.getSeq().equals(other.getSeq()))
            && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()))
            && (this.getIntro() == null ? other.getIntro() == null : this.getIntro().equals(other.getIntro()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSeq() == null) ? 0 : getSeq().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        result = prime * result + ((getIntro() == null) ? 0 : getIntro().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}