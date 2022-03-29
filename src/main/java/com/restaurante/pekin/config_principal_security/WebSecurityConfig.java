package com.restaurante.pekin.config_principal_security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.restaurante.pekin.configurationJwt.JWTConfigurer;
import com.restaurante.pekin.configurationJwt.TokenProvider;
import com.restaurante.pekin.security.JwtAccessDeniedHandler;
import com.restaurante.pekin.security.JwtAuthenticationEntryPoint;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final TokenProvider tokenProvider;
	private final CorsFilter corsFilter;
	private final JwtAuthenticationEntryPoint authenticationErrorHandler;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	public WebSecurityConfig(TokenProvider tokenProvider, CorsFilter corsFilter,
			JwtAuthenticationEntryPoint authenticationErrorHandler, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
		this.tokenProvider = tokenProvider;
		this.corsFilter = corsFilter;
		this.authenticationErrorHandler = authenticationErrorHandler;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}

	// Configure BCrypt password encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Configuracion de rutas y extensiones que permite Spring Security sin permisos
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/", "/*.jsp", "/favicon.ico", "/**/*.jsp",
				"/**/*.css", "/**/*.map", "/**/*.js", "/**/*.svg", "/**/*.jpg", "/**/*.png", "/**/*.jpeg",
				"/h2-console/**");
	}

	// Configure security settings
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.cors().and()
				.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
				.authenticationEntryPoint(authenticationErrorHandler).accessDeniedHandler(jwtAccessDeniedHandler)

				.and().headers().frameOptions().sameOrigin()

				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests()												
				//vistas
				.antMatchers("/api/authenticate").permitAll()
				.antMatchers("/index").permitAll()
				.antMatchers("/listarProductosIndex").permitAll()
				.antMatchers("/AltaCliente").permitAll()
				.antMatchers("/AltaRole").permitAll()				
				.antMatchers("/ClienteRole").permitAll()
				.antMatchers("/public-menu").permitAll()				
				.antMatchers("/insertarDetalleProducto").permitAll()			
				.antMatchers("/DetalleListaActivos").permitAll()
				
				.antMatchers("/insertarProducto").hasAuthority("ROLE_ADMIN")	
				.antMatchers("/listarProductos").hasAuthority("ROLE_ADMIN")	
				.antMatchers("/actualizarProducto").hasAuthority("ROLE_ADMIN")	
				.antMatchers("/eliminarMenu").hasAuthority("ROLE_ADMIN")	
																	
				.antMatchers("/insertarCliente").hasAuthority("ROLE_ADMIN")
				.antMatchers("/listaCliente").hasAuthority("ROLE_ADMIN")
				.antMatchers("/actualizarCliente").hasAuthority("ROLE_ADMIN")
				.antMatchers("/eliminarCliente").hasAuthority("ROLE_ADMIN")
				.antMatchers("/consultaIdClienteReciente").hasAuthority("ROLE_ADMIN")
				.antMatchers("/consultaClienteSesion").hasAuthority("ROLE_ADMIN")												
								
				.antMatchers("/insertarRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/listarRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/eliminarRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/actualizarRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/consultarIdRole").hasAuthority("ROLE_ADMIN")
				
				.antMatchers("/insertarClienteRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/listarClienteRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/actualizarClienteRole").hasAuthority("ROLE_ADMIN")
				
				.antMatchers("/listarDetalleProducto").hasAuthority("ROLE_ADMIN")				
				.antMatchers("/actualizarDetalle").hasAuthority("ROLE_ADMIN")
				
				// son Matchers pero para obtener recursos de post get put delete				
				.antMatchers("/redirect").permitAll()								

				// creo que no sirve para los dos solo uno debe tener ese ROLE
				.antMatchers("/api/cliente").hasAuthority("ROLE_ADMIN")
				.antMatchers("/api/consultaDetalleServicioCliente").hasAuthority("ROLE_USER")
				.anyRequest().authenticated()

				.and().formLogin().loginPage("/login-page").permitAll().and().logout().permitAll().and()

				.apply(securityConfigurerAdapter());
	}

	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}

