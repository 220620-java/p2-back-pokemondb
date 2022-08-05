package com.revature.pokemondb.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.PokemondbApplication;
import com.revature.pokemondb.auth.JwtConfig;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.utils.SecurityUtils;
import com.revature.pokemondb.exceptions.FailedAuthenticationException;

import io.jsonwebtoken.Jwts;

@SpringBootTest(classes=PokemondbApplication.class)
class TokenServiceTest {
    @Autowired
	private JwtConfig jwtConfig;
	@Autowired
	private TokenService tokenService;

    @MockBean
    private SecurityUtils mockUtils;
	
    /**
     * Creates a successful token.
     */
	@Test
	public void createTokenSuccess() {
		User user = new User();
		String jws = tokenService.createToken(user);
		
		assertDoesNotThrow(() -> 
			Jwts.parserBuilder().setSigningKey(jwtConfig.getSigningKey()).build().parseClaimsJws(jws)
		);
	}
	
    /**
     * Tries to create a token from a null user.
     */
	@Test
	public void createTokenNullUser() {
		assertEquals("", tokenService.createToken(null));
	}
	
    /**
     * Tries to create a token from invalid user configuration.
     */
	@Test
	public void createTokenInvalidUser() {
		assertEquals("", tokenService.createToken(new User(null, null, null)));
	}
	
    /**
     * Validates the token successfully.
     */
	@Test
	public void validateTokenSuccess() {
        long now = System.currentTimeMillis();
        User mockUser = new User();
        mockUser.setUserId(1l);
        mockUser.setUsername("colbytang");
        mockUser.setPassword("pass");
        mockUser.setEmail("ctang@email.com");
        mockUser.setRole("user");
		String validToken = Jwts.builder()
                .setId(String.valueOf(mockUser.getUserId()))
                .setSubject(mockUser.getUsername())
                .claim("role", mockUser.getRole())
                .setIssuer("pokepost")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSigningKey())
                .compact();
		
		assertDoesNotThrow(() -> {
			tokenService.validateToken(validToken);
		});
	}

    /**
     * Should fail to validate an expired token.
     */
	@Test
	public void validateExpiredToken() {
        long now = System.currentTimeMillis();
        User mockUser = new User();
        mockUser.setUserId(1l);
        mockUser.setUsername("colbytang");
        mockUser.setPassword("pass");
        mockUser.setEmail("ctang@email.com");
        mockUser.setRole("user");
		String validToken = Jwts.builder()
                .setId(String.valueOf(mockUser.getUserId()))
                .setSubject(mockUser.getUsername())
                .claim("role", mockUser.getRole())
                .setIssuer("pokepost")
                .setIssuedAt(new Date(now))
                // Set expiration to before now.
                .setExpiration(new Date(now - jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSigningKey())
                .compact();
		
            assertThrows(FailedAuthenticationException.class, () -> {
			tokenService.validateToken(validToken);
		});
	}
	
    /**
     * Should fail authentication on an invalid token.
     */
	@Test
	public void validateTokenInvalidToken() {
		assertThrows(FailedAuthenticationException.class, () -> {
			tokenService.validateToken("aaaaa");
		});
	}
}
