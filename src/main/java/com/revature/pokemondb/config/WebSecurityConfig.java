package com.revature.pokemondb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// @EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Disables Cross-Site Request Forgery (CSRF)
     * https://owasp.org/www-community/attacks/csrf
     * Might implement a CSRF token instead: https://www.baeldung.com/spring-security-csrf
     * Customizing filter chain: https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-requests.html
     * @param http
     * @return
     * @throws Exception
     */
	// @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.csrf().disable()
    //     .httpBasic();
    //     return http.build();
    // }

    // This does some cors stuff maybe I think lol
	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
					.allowedOrigins("*")
					.allowedHeaders("*")
					.exposedHeaders("*")
					.allowCredentials(false)
					.maxAge(3600);
			}
		};
	}
}