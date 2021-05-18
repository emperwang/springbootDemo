package com.test.wk.entity;


import com.test.wk.utils.EncryptUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String userName;
    private String password;
    private List<Role> roles;
    private boolean anonymous;

    public User() {
        roles = new ArrayList<>();
        anonymous = false;
    }

    public User(String username, String password) {
        this.userName = username;
        this.password = password;
        roles = new ArrayList<>();
        anonymous = false;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return EncryptUtil.decryptBase64(password);
    }

    public void setPassword(String password) throws UnsupportedEncodingException {
        this.password = EncryptUtil.encryptBase64(password);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, roles);
    }
}
