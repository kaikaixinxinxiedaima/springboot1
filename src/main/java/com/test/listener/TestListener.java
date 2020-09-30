package com.test.listener;

import com.test.controller.LoginController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class TestListener {
    private static Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping("event")
    public String getEvent(){
        context.publishEvent(new MyEvent("小刚"));
        return "hello";
    }

    /**
     * 登录
     */
    @RequestMapping("/login")
    public void getUserByUserNameAndPassword(String username, String password, HttpSession session, HttpServletRequest request) {
        logger.info("用户【"+username+"】登陆开始！");
        if("admin".equals(username) && "123456".equals(password)){
            request.getSession().setAttribute("loginName",username);
            logger.info("用户【"+username+"】登陆成功！");
        }else{
            logger.info("用户【"+username+"】登录失败！");
        }
    }
    /**
     *查询在线人数
     */
    @RequestMapping("/online")
    public String online() {
        return  "当前在线人数：" + MyHttpSessionListener.count.get() + "人";
    }
    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public void Logout( HttpServletRequest request) {
        logger.info("用户退出登录开始！");
        HttpSession session = request.getSession(false);//防止创建Session
        if(session != null){
            session.removeAttribute("loginName");
            session.invalidate();
        }
        logger.info("用户退出登录结束！");
    }
}
