package com.test.wk.service;

import com.test.wk.common.Common;
import com.test.wk.entity.Role;
import com.test.wk.entity.User;
import com.test.wk.utils.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public boolean addUser(String username, String pwd) {
        return Common.getUserinfos().add(new User(username, pwd));
    }

    @Override
    public boolean addUser(User user) {
        return Common.getUserinfos().add(user);
    }

    @Override
    public boolean addRole(String rolename) {
        return Common.getRoles().add(new Role(rolename));
    }

    @Override
    public boolean addRole(Role role) {
        return Common.getRoles().add(role);
    }

    @Override
    public boolean deleteUser(String username, String pwd) {
        Set<User> userinfos = Common.getUserinfos();
        User user = getUser(username,pwd);
        if (user != null) {
            return userinfos.remove(user);
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        Set<User> userinfos = Common.getUserinfos();
        if (user != null) {
            return userinfos.remove(user);
        }
        return false;
    }

    private User getUser(String username, String pwd){
        Set<User> userinfos = Common.getUserinfos();
        User user = null;
        for (User userinfo : userinfos) {
            if (userinfo.getUserName().equalsIgnoreCase(username) && userinfo.getPassword().equalsIgnoreCase(pwd)){
                user = userinfo;
                break;
            }
        }
        return user;
    }

    private boolean containRole(User user, String roleName){
        List<Role> roles = user.getRoles();
        return roles.contains(new Role(roleName));
    }

    private boolean isValidRole(String roleName){
        Set<Role> roles = Common.getRoles();
        return roles.contains(new Role(roleName));
    }

    @Override
    public boolean assignRoleToUser(String username, String pwd,String roleName) {
        User user = getUser(username, pwd);
        if (user.isAnonymous()){
            logger.info("the use : {}  is anonymous", username);
            return false;
        }
        if (isValidRole(roleName) && !containRole(user, roleName)){
            logger.info("assign role to user.");
            return user.getRoles().add(new Role(roleName));
        }
        logger.info("the role already assign to user.");
        return false;
    }

    @Override
    public String authenticate(String username, String pwd) throws UnsupportedEncodingException {
        User user = getUser(username, pwd);
        if (user != null){
            StringBuilder append = new StringBuilder().append(username).append(pwd);
            return EncryptUtil.encryptBase64(append.toString());
        }
        return null;
    }

    @Override
    public String authenticate(User user) throws UnsupportedEncodingException {
        if (isValidUser(user)){
            StringBuilder append = new StringBuilder().append(user.getUserName()).append(user.getPassword());
            String token = EncryptUtil.encryptBase64(append.toString());
            Common.getTokens().put(token,System.currentTimeMillis());
            return token;
        }
        return null;
    }

    private boolean isValidUser(User user){
        if (user == null || (StringUtils.isEmpty(user.getUserName()) && StringUtils.isEmpty(user.getPassword()))){
            return false;
        }
        Set<User> userinfos = Common.getUserinfos();
        for (User userinfo : userinfos) {
            if (userinfo.getUserName().equalsIgnoreCase(user.getUserName()) && userinfo.getPassword().equalsIgnoreCase(user.getPassword())){
                return true;
            }
        }
        return false;
    }
}
