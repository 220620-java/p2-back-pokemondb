package com.revature.pokemondb.aspects;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("allPokeApp()")
    public Object logAdvice(ProceedingJoinPoint joinpoint) throws Throwable {
		// getting the class that the joinpoint is part of so that the logger matches
		Class<?> joinPointClass = joinpoint.getTarget().getClass();

		// this creates a logger for that class
		Logger logger = LoggerFactory.getLogger(joinPointClass);

		logger.info("Method called: " + joinpoint.getSignature().toShortString());
		logger.info("with arguments: " + Arrays.toString(joinpoint.getArgs()));

		Object returnVal;

		try {
			// allowing the method to actually execute
			returnVal = joinpoint.proceed();
		} catch (Throwable e) {
			logger.error(e.getClass() + ": " + e.getMessage());
			// don't forget to still throw the throwable
			throw e;
		}

		logger.info("Method returned: " + returnVal);
		// don't forget to return the return value
		return returnVal;
	}

    // in order to make a reusable pointcut expression,
	// we can use a hook method (method with an empty body used to hold annotations)
	@Pointcut("execution(* com.revature.pokemondb..*(..) )")
	public void allPokeApp() {}
}
