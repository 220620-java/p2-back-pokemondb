package com.revature.pokemondb.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.pokemondb.auth.JwtConfig;
import com.revature.pokemondb.exceptions.FailedAuthenticationException;
import com.revature.pokemondb.exceptions.TokenExpirationException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.dtos.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Service
public class TokenServiceImpl implements TokenService {
    private JwtConfig jwtConfig;

    public TokenServiceImpl (JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    /**
     * 
     */
    @Override
    public String createToken(User user) {
        String jws = "";

        if (user != null && user.getUsername() != null) {
            long now = System.currentTimeMillis();

            jws = Jwts.builder()
                .setId(String.valueOf(user.getUserId()))
                .setSubject(user.getUsername())
                .setIssuer("pokepost")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSigningKey())
                .compact();
        }

        return jws;
    }

    @Override
    public int getDefaultExpiration() {
        return jwtConfig.getExpiration();
    }

    @Override
    public Optional<UserDTO> validateToken(String token)
            throws FailedAuthenticationException, TokenExpirationException {
        try {
            Claims jwtClaims = Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
            long now = System.currentTimeMillis();
            if (jwtClaims.getExpiration().before(new Date(now))) {
                throw new TokenExpirationException();
            }

            UserDTO userDTO = parseUser(jwtClaims);

            return Optional.of(userDTO);
        } catch (JwtException e) {
            throw new FailedAuthenticationException();
        }
    }

    private UserDTO parseUser(Claims claims) {
		Long id = Long.parseLong(claims.getId());
		String username = claims.getSubject();
		// Role role = new Role();
		// role.setName(claims.get("role").toString());
		
		// return new UserDTO(id, username, role, null);
		return new UserDTO(id, username);
	}
}
