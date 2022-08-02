package com.revature.pokemondb.aspects;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.revature.pokemondb.auth.Auth;
import com.revature.pokemondb.exceptions.TokenExpirationException;
import com.revature.pokemondb.models.dtos.UserDTO;
import com.revature.pokemondb.services.TokenService;
import com.revature.pokemondb.exceptions.FailedAuthenticationException;

@Aspect
@Component
public class AuthAspect {
	private TokenService tokenServ;
	private HttpServletRequest currentReq;
	
	public AuthAspect(TokenService tokenServ, HttpServletRequest req) {
		this.tokenServ = tokenServ;
		this.currentReq = req;
	}
	
	@Around("methodsWithAuthAnnotation()")
	public Object authenticate(ProceedingJoinPoint joinpoint) throws Throwable {
		Auth authAnnotation = ((MethodSignature) joinpoint.getSignature())
				.getMethod()
				.getAnnotation(Auth.class);
		String requiredRole = authAnnotation.requiredRole();
		
		String jws = currentReq.getHeader("Auth");
		Optional<UserDTO> userDtoOpt = Optional.empty();
		try {
			userDtoOpt = tokenServ.validateToken(jws);
		} catch (FailedAuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
		} catch (TokenExpirationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired token.");
		}
		
		if (!userDtoOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user info present.");
		}
		
		switch (requiredRole) {
		case "admin":
			String roleName = userDtoOpt.get().getRole();
			if (!roleName.toLowerCase().equals("admin")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient privileges.");
			}
			break;
		}
		
		return joinpoint.proceed();
	}
	
	@Pointcut("@annotation(com.revature.petapp.auth.Auth)")
	public void methodsWithAuthAnnotation() {}
}
