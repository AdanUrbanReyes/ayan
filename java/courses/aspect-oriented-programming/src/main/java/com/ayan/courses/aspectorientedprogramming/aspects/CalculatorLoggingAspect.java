package com.ayan.courses.aspectorientedprogramming.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Log4j2
/**
 * This basically is an aspect; code that is share over multiple
 * objects. Like logging, transaction, cache etc.
 */
public class CalculatorLoggingAspect {

    @Before("execution(* com.ayan.courses.aspectorientedprogramming.components.ArithmeticCalculatorComponent.product(..))")
    /**
     * This is an advice; basically code that can be execute before, after, after run, around the actual method.
     * The POINTCUT is the expression inside <code>@Before</code> annotation which helps to match which methods will
     * be affected by this advice aspect
     */
    public void beforeProduct() {
        log.info("Here you execute the advice before the actual method get executed");
    }

    @After("execution(* com.ayan.courses.aspectorientedprogramming.components.ArithmeticCalculatorComponent.divide(..))")
    public void afterDivide() {
        log.info("Here you execute the advice after the actual method get executed; no mather if throw an exception or ends well");
    }

    @AfterReturning(pointcut = "execution(* com.ayan.courses.aspectorientedprogramming.components.ArithmeticCalculatorComponent.subtract(..))"
            , returning = "result")
    public void afterReturningSubtract(double result) {
        log.info("Here you execute the advice after the value is returned ({}); this advice not will be executed if and exception is throw", result);
    }

    @AfterThrowing(pointcut = "execution(* com.ayan.courses.aspectorientedprogramming.components.ArithmeticCalculatorComponent.divide(..))"
            , throwing = "exception")
    public void afterThrowingDivide(Throwable exception) {
        log.info("Here you execute the advice after the actual method throw an exception ({})", exception.getMessage());
    }

    @Before("execution(* com.ayan.courses.aspectorientedprogramming.components.ArithmeticCalculatorComponent.addition(..))")
    public void beforeAddition(JoinPoint joinPoint) {
        log.info("kind : {}", joinPoint.getKind());
        log.info("method signature : {}", joinPoint.getSignature().getName());
        log.info("declaring type name : {}", joinPoint.getSignature().getDeclaringTypeName());
        log.info("method arguments : {}", Arrays.toString(joinPoint.getArgs()));
        log.info("target object : {}", joinPoint.getTarget());
        log.info("this object {}", joinPoint.getThis());
    }

    

}
