package com.mycreazy.springbootweb.service.impl;


import com.mycreazy.springbootweb.dao.BaseDao;
import com.mycreazy.springbootweb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Description
 **/
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BaseDao baseDao;

    @Override
    public int login(String username, String password, HttpServletRequest request) {
        List<Map<String, Object>> list = baseDao.query("login_account_info", "account", username);
        if (list == null || list.size() == 0)
            return 0;

        Map<String, Object> map = list.get(0);
        String spassword = String.valueOf(map.get("password"));

        if (password == null || !spassword.equals(password))
            return -1;

        HttpSession session = request.getSession();
        session.setAttribute("__user", username);
        session.setAttribute("__status", "1");
        session.setAttribute("__isadmin", map.get("loginerflag"));
        session.setAttribute("__nickname", map.get("nickname"));
        session.setAttribute("__level", map.get("level"));
        return 1;
    }
}
