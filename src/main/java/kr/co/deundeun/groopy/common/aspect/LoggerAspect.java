package kr.co.deundeun.groopy.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class LoggerAspect {
    @Pointcut("execution(* kr.co.deundeun.groopy.controller..*(..))") // 이런 패턴이 실행될 경우 수행
    public void loggerPointCut() {
    }

    @Before("loggerPointCut()") // advice -> aspect -> controller 순서 실행
    public void beforeController(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String controllerName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Map<String, Object> params = new HashMap<>();

        try {
            params.put("controller", controllerName);
            params.put("method", methodName);
            params.put("log_time", new Date());
            params.put("request_uri", request.getRequestURI());
            params.put("http_method", request.getMethod());
        } catch (Exception e) {
            log.error("LoggerAspect error", e);
        }
        log.info("request : {}", params); // param에 담긴 정보들을 한번에 로깅한다.
    }

    @AfterReturning("loggerPointCut()") // 정상 반환시, controller -> aspect -> advice 순서 실행
    public void afterController(JoinPoint joinPoint) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        Map<String, Object> params = new HashMap<>();

        try {
            params.put("status_code", response.getStatus());
        } catch (Exception e) {
            log.error("LoggerAspect error", e);
        }
        log.info("response : {}", params); // param에 담긴 정보들을 한번에 로깅한다.
    }

    @AfterThrowing(pointcut = "loggerPointCut()", throwing = "e") // 에러시 실행
    public void afterThrowingController(JoinPoint joinPoint, Exception e) {

        Map<String, Object> params = new HashMap<>();

        try {
            params.put("exception", e);
            params.put("message", e.getMessage());
        } catch (Exception ex) {
            log.error("LoggerAspect error", ex);
        }
        log.error("error : {}", params);
    }


}