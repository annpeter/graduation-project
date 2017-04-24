package cn.annpeter.graduation.project.dal.model;

import java.io.Serializable;

public class HomeWorkCommit implements Serializable {
    /**
     * INTEGER(10) 必填
     * 
     */
    private Integer id;

    /**
     * INTEGER(10)
     * 用户id
     */
    private Integer userId;

    /**
     * VARCHAR(256)
     * 作业地址
     */
    private String url;

    /**
     * REAL(12)
     * 得分
     */
    private Float score;

    /**
     * VARCHAR(256)
     * 评论
     */
    private String comment;

    /**
     * INTEGER(10) 必填
     * 作业id
     */
    private Integer homeWorkId;

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
     * 获得 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * INTEGER(10)
     * 设置 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * VARCHAR(256)
     * 获得 作业地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * VARCHAR(256)
     * 设置 作业地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * REAL(12)
     * 获得 得分
     */
    public Float getScore() {
        return score;
    }

    /**
     * REAL(12)
     * 设置 得分
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * VARCHAR(256)
     * 获得 评论
     */
    public String getComment() {
        return comment;
    }

    /**
     * VARCHAR(256)
     * 设置 评论
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * INTEGER(10) 必填
     * 获得 作业id
     */
    public Integer getHomeWorkId() {
        return homeWorkId;
    }

    /**
     * INTEGER(10) 必填
     * 设置 作业id
     */
    public void setHomeWorkId(Integer homeWorkId) {
        this.homeWorkId = homeWorkId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", url=").append(url);
        sb.append(", score=").append(score);
        sb.append(", comment=").append(comment);
        sb.append(", homeWorkId=").append(homeWorkId);
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
        HomeWorkCommit other = (HomeWorkCommit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getHomeWorkId() == null ? other.getHomeWorkId() == null : this.getHomeWorkId().equals(other.getHomeWorkId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getHomeWorkId() == null) ? 0 : getHomeWorkId().hashCode());
        return result;
    }
}