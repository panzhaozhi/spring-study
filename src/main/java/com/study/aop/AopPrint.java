package com.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopPrint {

    @Pointcut("@annotation(com.study.aop.Pan)")
    public void pt(){

    }

    @Around(value = "pt() && @annotation(pan)")
    public void around(ProceedingJoinPoint joinPoint, Pan pan) throws Throwable {
        System.out.println("hello . ..");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Pan getType = methodSignature.getMethod().getAnnotation(Pan.class);
        Object[] args = joinPoint.getArgs();
        System.out.println(args.length);
        joinPoint.proceed();
    }

}
