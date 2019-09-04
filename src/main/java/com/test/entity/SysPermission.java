package com.test.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity //实体类的注解
@Table(name="sys_permission")
public class SysPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private String resUrl;

    private String userType;

    private String parentId;

    private String userSort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUserSort() {
        return userSort;
    }

    public void setUserSort(String userSort) {
        this.userSort = userSort;
    }
}
