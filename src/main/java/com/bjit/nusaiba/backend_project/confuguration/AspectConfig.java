package com.bjit.nusaiba.backend_project.confuguration;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    Logger logger = LoggerFactory.getLogger(AspectConfig.class);

    @Before("execution(* com.bjit.nusaiba.backend_project.controller.*.*(..))")
    public void beforeExecution(JoinPoint joinPoint) {
        logger.info("Executing {}",joinPoint);
    }

    @After("execution(* com.bjit.nusaiba.backend_project.controller.*.*(..))")
    public void afterExecution(JoinPoint joinPoint) {
        logger.info("Completed {}",joinPoint);
    }
    


}

