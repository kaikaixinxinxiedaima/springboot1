package com.test.interceptor;

import com.test.aop.LogAspect;
import com.test.util.DateUtils;
import com.test.util.HttpContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

public class LogInterceptor implements HandlerInterceptor {
	private final static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

	private static final ThreadLocal<Long> startTimeThreadLocal =
			new NamedThreadLocal<Long>("ThreadLocal StartTime");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
							 Object handler) throws Exception {

		long beginTime = System.currentTimeMillis();//1、开始时间
		startTimeThreadLocal.set(beginTime);        //线程绑定变量（该数据只有当前请求的线程可见）
		logger.debug("开始计时: {} IP: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
				.format(beginTime), HttpContextUtils.getIpAddress(),request.getRequestURI());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null){
			logger.info("ViewName: " + modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {


		// 打印JVM信息。
		long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
		long endTime = System.currentTimeMillis();    //2、结束时间
		logger.debug("结束时间：{}  耗时：{}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
				new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateUtils.formatDateTime(endTime - beginTime),
				Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
				(Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024);

	}

}
