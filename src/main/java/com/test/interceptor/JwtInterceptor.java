package com.test.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.test.annotation.jwt.UserLoginToken;
import com.test.util.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    /**
     * token默认的长度
     */
    private static String header;

    @Value("${config.jwt.header}")
    public void setHeader(String header) {
        this.header = header;
    }


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(header);
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken jwtToken = method.getAnnotation(UserLoginToken.class);
            if (jwtToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 userName
//                String userId = JWTUtil.getUserId(token);
//                System.out.println("用户id:" + userId);
//
//                // 验证 token
//                JwtUtil.checkSign(token);

                DecodedJWT verify = JWTUtil.verify(token);
                String name = verify.getClaim("name").asString();
                System.out.println("name: " + name);

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}