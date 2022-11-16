package aop.stage1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EdenAspect {

    @Before("execution(* aop.stage1.UserService.changePassword())")
    public Object test(JoinPoint joinPoint) {
        System.out.println("\"HI\" = " + "HI");
        return null;
    }
}
