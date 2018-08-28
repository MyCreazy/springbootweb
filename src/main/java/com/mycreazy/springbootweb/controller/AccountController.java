package com.mycreazy.springbootweb.controller;

import com.mycreazy.springbootweb.service.AccountService;
import com.mycreazy.springbootweb.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Time: 上午10:18
 **/
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 日志对象
     */
    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping("/login/entry")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, String user, String pwd) {
        String uniqueID = LogUtil.generateUniqueID(user);
        String json = "{\"account\":\"" + user + "\",\"password\":\"" + pwd + "\"";
        int result = 0;
        String tempResult="";
        try {
            logger.info(uniqueID + "|request|" + json);
            result = accountService.login(user, pwd, request);
            if (result == 1) {
                tempResult="登陆成功";
                return Result.OK("登陆成功");
            } else if (result == 0) {
                tempResult="未找到账户";
                return Result.ERROR("未找到账户");
            } else if (result == -1) {
                tempResult="密码错误";
                return Result.ERROR("密码错误");
            }
        } catch (Exception ex) {
            logger.error(uniqueID+"登录发生异常:"+ex.getMessage());

        } finally {
            logger.info(uniqueID + "|response|" + tempResult);
        }

        return Result.ERROR();
    }

    @RequestMapping("/login/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("__user");
        session.removeAttribute("__status");
        session.removeAttribute("__nickname");
        session.removeAttribute("__level");

        return Result.OK();
    }

    @RequestMapping("/account/get")
    @ResponseBody
    public Map<String, Object> get(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        HttpSession session = request.getSession();
        map.put("user", session.getAttribute("__user"));
        map.put("status", session.getAttribute("__status"));
        map.put("__isadmin", session.getAttribute("__isadmin"));
        map.put("nickname", session.getAttribute("__nickname"));
        map.put("level", session.getAttribute("__level"));

        return Result.OK(map);
    }
}
