package com.lhl.controller;

import com.lhl.domain.SysLog;
import com.lhl.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @athor:lhl
 * @create:2020-02-11 17:54
 */
@Component
@Aspect
public class LogAop {

    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法


    @Autowired
    HttpServletRequest request;

    @Autowired
    ISysLogService sysLogService;

    @Before("execution(* com.lhl.controller.*.*(..))&&!execution(* com.lhl.controller.LogAop.*(..))&&!execution(* com.lhl.controller.SysLogController.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        //获取开始执行的时间
        startTime=new Date();

        //获取访问的类
        executionClass=jp.getTarget().getClass();

        //获取访问的方法
        String methodName=jp.getSignature().getName();
            //获取方法的参数字节码数组 args[]
        Object[] jpArgs = jp.getArgs();
        Class[] args=new Class[jpArgs.length];
        for (int i=0;i<args.length;i++) {
            args[i]=jpArgs[i].getClass();
        }
        executionMethod = executionClass.getMethod(methodName, args);

    }

    @After("execution(* com.lhl.controller.*.*(..))&&!execution(* com.lhl.controller.LogAop.*(..))&&!execution(* com.lhl.controller.SysLogController.*(..))")
    public void doAfter(JoinPoint jp){

        //判断是否为空
        if (executionClass==null){
            return;
        }

        //计算花费时间
        Date endTime=new Date();
        long totalTime=endTime.getTime()-startTime.getTime();

        //获取访问的url
        RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
        String[] url1 = classAnnotation.value();//获取第一部分的url
        RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
        String[] url2 = methodAnnotation.value();//获取第二部分的url
        String url=url1[0]+url2[0];

        //获取IP地址,从request中获取,需要在web.xml中添加RequestContextListener监听器
        String IP = request.getRemoteAddr();

        //获取当前用户
        //1
        SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        //2
//        SecurityContext securityContext = SecurityContextHolder.getContext();
        User principal = (User) securityContext.getAuthentication().getPrincipal();
        String username = principal.getUsername();

        SysLog sysLog=new SysLog();
        sysLog.setVisitTime(startTime);
        sysLog.setUsername(username);
        sysLog.setIp(IP);
        sysLog.setUrl(url);
        sysLog.setExecutionTime(totalTime);
        sysLog.setMethod(executionClass.getName()+"."+executionMethod.getName()+"()");
        sysLogService.save(sysLog);
    }


}
