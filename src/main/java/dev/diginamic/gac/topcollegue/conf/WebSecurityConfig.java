package dev.diginamic.gac.topcollegue.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		        .csrf().disable().cors().and()
		        .authorizeRequests()

		        // .antMatchers(HttpMethod.POST, "/auth").permitAll()
		        // on autorise tout pour pouvoir utiliser l'aplli avant de gerer la connexion
		        .anyRequest().permitAll()

		        /*
		         * .antMatchers("/h2-console/**").permitAll()
		         * 
		         * .antMatchers(HttpMethod.POST, "/auth").permitAll()
		         * 
		         * .anyRequest().authenticated()
		         */
		        .and().headers().frameOptions().disable()

		        .and()
		        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)

		        .logout()
		        // en cas de succès un OK est envoyé (à la place d'une redirection vers /login)
		        .logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
		        // suppression du cookie d'authentification
		        .deleteCookies(TOKEN_COOKIE); // Gestion de la déconnexion;
		;

	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/logout", config);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
