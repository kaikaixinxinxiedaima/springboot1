package com.test.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity //实体类的注解
@Table(name="sys_user")
public class SysUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private String passWord;

    private int userEnable;

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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getUserEnable() {
        return userEnable;
    }

    public void setUserEnable(int userEnable) {
        this.userEnable = userEnable;
    }
}
