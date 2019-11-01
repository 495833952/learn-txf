package com.txf.aop;

import com.txf.common.PageResultBean;
import com.txf.common.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 类定义为切面类
 */
@Aspect
@Component
public class AopTestController {
    private static final Logger logger = LoggerFactory.getLogger(AopTestController.class);

    ThreadLocal <ResultBean> resultBeanThreadLocal = new ThreadLocal < > ();
    ThreadLocal <PageResultBean<? >> pageResultBeanThreadLocal = new  ThreadLocal < > ();
    ThreadLocal < Long > start = new ThreadLocal < > ();
    /**
     * 定义一个切点
     */
    @Pointcut(value = "execution(public String test (..))")
    public void cutOffPoint() {
    }


/*    @Before("cutOffPoint()")
    public void beforeTest (){
        logger.info("我在test方法之前执行");
    }

    @After("cutOffPoint()")
    public void  doAfter (){
        logger.info("我是在test之后执行的");
    }*/

    ThreadLocal < Long > startTime = new ThreadLocal < > ();
    @Around("cutOffPoint()")
    public  Object doAround(ProceedingJoinPoint pjp)
    {
        startTime.set(System.currentTimeMillis());
        logger.info("我是环绕通知执行");
        Object obj;
        try
        {
            obj = pjp.proceed();
            logger.info("执行返回值 : " + obj);
            logger.info(pjp.getSignature().getName() + "方法执行耗时: " + (System.currentTimeMillis() - startTime.get()));
        }
        catch(Throwable throwable)
        {
            obj = throwable.toString();
        }
        return obj;
    }

    /**
     * 执行完请求可以做的
     * @param result
     * @throws Throwable
     */
    @AfterReturning(returning = "result", pointcut = "cutOffPoint()")
    public void doAfterReturning(Object result) throws Throwable
    {
//        String aa="11a";
//        int a=parseInt(aa);
        logger.info("大家好，我是@AfterReturning，他们都秀完了，该我上场了");
    }

    // 声明错误e时指定的抛错类型法必会抛出指定类型的异常
    // 此处将e的类型声明为Throwable，对抛出的异常不加限制
    @AfterThrowing(throwing = "e", pointcut = "cutOffPoint()")
    public void doAfterReturning(Throwable e){
        logger.info("大家好，我是@AfterThrowing，他们犯的错误，我来背锅");
        logger.info("错误信息" + e.getMessage());
    }


  /** --------------------------------------------------------------------------------------------------   **/


    @Pointcut(value = "execution(public com.txf.common.PageResultBean *(..)))")
    public void handlerPageResultBeanMethod(){

    }

    @Pointcut(value = "execution(public com.txf.common.ResultBean *(..)))")
    public void handlerResultBeanMethod(){

    }

    @Around("handlerPageResultBeanMethod()")
    public Object handlerPageResultBeanMethod(ProceedingJoinPoint pjp){
        start.set(System.currentTimeMillis());
        try{
            pageResultBeanThreadLocal.set((PageResultBean <? > ) pjp.proceed());
            logger.info(pjp.getSignature() + " 方法执行耗时:" + (System.currentTimeMillis() - start.get()));
        }catch(Throwable e){
            ResultBean <? > resultBean = handlerException(pjp, e);
            pageResultBeanThreadLocal.set(new PageResultBean <> ().setMsg(resultBean.getMsg()).setCode(resultBean.getCode()));
        }
        return pageResultBeanThreadLocal.get();
    }

    @Around("handlerResultBeanMethod()")
    public Object handlerResultBeanMethod(ProceedingJoinPoint pjp){
        start.set(System.currentTimeMillis());
        try{
            resultBeanThreadLocal.set((ResultBean <? > ) pjp.proceed());
            logger.info(pjp.getSignature() + " 方法执行耗时:" + (System.currentTimeMillis() - start.get()));
        }catch(Throwable e){
            resultBeanThreadLocal.set(handlerException(pjp, e));
        }
        return resultBeanThreadLocal.get();
    }
    /**
     * 封装异常信息，注意区分已知异常（自己抛出的）和未知异常
     */
    private ResultBean <? > handlerException(ProceedingJoinPoint pjp, Throwable e)
    {
        ResultBean <? > result = new PageResultBean();
        logger.error(pjp.getSignature() + " error ", e);
        // 已知异常
//        if(e instanceof CheckException){
//            result.setMsg(e.getLocalizedMessage());
//            result.setCode(ResultBean.FAIL);
//        }else if(e instanceof UnloginException){
//            result.setMsg("Unlogin");
//            result.setCode(ResultBean.NO_LOGIN);
//        } else {
            result.setMsg(e.toString());
            result.setCode(ResultBean.FAIL);
//        }
        return result;
    }

}

