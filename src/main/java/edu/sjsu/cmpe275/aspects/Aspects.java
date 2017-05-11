package edu.sjsu.cmpe275.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import edu.sjsu.cmpe275.model.CustomException;

@Aspect
public class Aspects {
	
	Logger logger;
	
	@Before("execution(* *(..)) && @annotation(Loggable)")
	public void beforeLoggableMethod(JoinPoint joinPoint){
		logger = Logger.getLogger(joinPoint.getSignature().getClass());
		
		logger.info("Going into method: " + joinPoint.getSignature().getName() + " of class: " + joinPoint.getSignature().getClass().getName());
	}
	
	
	@After("execution(* *(..)) && @annotation(Loggable)")
	public void afterLoggableMethod(JoinPoint joinPoint){
		logger = Logger.getLogger(joinPoint.getSignature().getClass());
		
		logger.info("Moving out of method: " + joinPoint.getSignature().getName() + " of class: " + joinPoint.getSignature().getClass().getName());
	}
	
	@Around("execution(* *(..)) && @annotation(Secured)")
	public void aroundAnyMethod(ProceedingJoinPoint proceedingJoinPoint){
		logger = Logger.getLogger(proceedingJoinPoint.getSignature().getClass());
		
		try{
			
			proceedingJoinPoint.proceed();
			
		}catch(CustomException ce){
			logger.error("Error with response code: " + ce.getCode() + " and message: " + ce.getMessage());
			
			//reroute to error handling controller
		}catch(Exception e){
			logger.error("Unexpected exception: " + e.getMessage());
			e.printStackTrace();
			
			//reroute to error handling controller
			
		}catch (Throwable e) {
			logger.error("Unexpected Throwable error: " + e.getMessage());
			e.printStackTrace();
			
			//reroute to error handling controller
		}
	}
}
