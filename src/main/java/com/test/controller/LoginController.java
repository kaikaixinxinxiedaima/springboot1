package com.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;


@Controller
@RequestMapping(value = "/auth")
public class LoginController {

    @RequestMapping(value = "/login")
    public String submitLogin(String username, String password, HttpServletRequest request) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            SysUser user = (SysUser) subject.getPrincipal();
            subject.hasRole("admin");
        } catch (DisabledAccountException e) {
            request.setAttribute("msg", "账户已被禁用");
            return "login";
        } catch (AuthenticationException e) {
            request.setAttribute("msg", "用户名或密码错误");
            return "login";
        }

        // 执行到这里说明用户已登录成功
        return "/search/index";
    }


    @RequestMapping(value = "/err")
    public String err() {
        return "err";
    }

    @RequestMapping(value = "/index")
    public String loginSuccessMessage(HttpServletRequest request) {
        return "redirect:/search";
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        return "";
    }

    //被踢出后跳转的页面
    @RequestMapping(value = "/kickout")
    public String kickOut() {
        return "kickout";
    }


    public static void main(String[] args) {
        String valueSet = "1a1a1|公司1,2b2c2|公司2,3v3b3|公司3";
        String[] split = valueSet.split("\\|")[0].split(",");
        System.out.println(JSONObject.toJSONString(split));
    }
}