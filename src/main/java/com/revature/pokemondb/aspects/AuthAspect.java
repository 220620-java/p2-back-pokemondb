package com.revature.pokemondb.aspects;

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
	private TokenService tokenService;
	private HttpServletRequest currentReq;
	
	public AuthAspect(TokenService tokenService, HttpServletRequest req) {
		this.tokenService = tokenService;
		this.currentReq = req;
	}
	
	@Around("methodsWithAuthAnnotation()")
	public Object authenticate(ProceedingJoinPoint joinpoint) throws Throwable {
		Auth authAnnotation = ((MethodSignature) joinpoint.getSignature())
				.getMethod()
				.getAnnotation(Auth.class);
		String requiredRole = authAnnotation.requiredRole();
		boolean stopBannedUsers = authAnnotation.stopBannedUsers();
		boolean requireSelfAction = authAnnotation.requireSelfAction();
		
		String jws = currentReq.getHeader("Auth");
		String currentUser = currentReq.getHeader("Username");


		// If user has an empty token header
		if (jws == null || jws.equals("")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Empty token.");
		}

		Optional<UserDTO> userDtoOpt = Optional.empty();
		try {
			userDtoOpt = tokenService.validateToken(jws);
		} catch (FailedAuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
		} catch (TokenExpirationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired token.");
		}
		
		if (!userDtoOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user info present.");
		}
		
		if (requiredRole.equals("admin")) {
			String roleName = userDtoOpt.get().getRole();
			if (!roleName.toLowerCase().equals("admin")) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient privileges.");
			}
		}

		String username = userDtoOpt.get().getUsername();
		Long userId = userDtoOpt.get().getUserId();

		if (stopBannedUsers && tokenService.isUserBanned(userId)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorized. User has been banned.");
		}

		if (requireSelfAction) {
			if (currentUser == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No username in header. Insufficient privileges.");
			}
			
			if (!currentUser.equals(username)) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username does not match token! Insufficient privileges.");
			}
		}
		
		return joinpoint.proceed();
	}
	
	@Pointcut("@annotation(com.revature.pokemondb.auth.Auth)")
	public void methodsWithAuthAnnotation() 
	{ // I don't know why this has to be empty but it do
	}
}
