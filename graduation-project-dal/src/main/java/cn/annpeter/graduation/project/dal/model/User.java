package cn.annpeter.graduation.project.dal.model;

import java.io.Serializable;

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
     * VARCHAR(20) 必填
     * 密码
     */
    private String pwd;

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
     * VARCHAR(20) 必填
     * 获得 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * VARCHAR(20) 必填
     * 设置 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
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
            && (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
        return result;
    }
}