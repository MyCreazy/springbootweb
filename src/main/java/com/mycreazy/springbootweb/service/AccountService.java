package com.mycreazy.springbootweb.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Date: 2018/8/14
 * Time: 上午10:28
 **/
public interface AccountService {
    int login(String username, String password, HttpServletRequest request);
}
