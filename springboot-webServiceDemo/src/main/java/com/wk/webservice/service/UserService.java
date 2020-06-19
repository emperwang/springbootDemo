package com.wk.webservice.service;

import com.wk.webservice.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface UserService {

    @WebMethod
    User getUser();
}
