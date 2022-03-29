package com.restaurante.pekin.configurationJwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTFilter extends GenericFilterBean {

	private static final Logger LOG = LoggerFactory.getLogger(JWTFilter.class);

	public static final String AUTHORIZATION_HEADER = "Authorization";

	
	private final List<String> PATHS_TO_IGNORE_SETTING_SAMESITE = Arrays.asList("resources","/index");
    private final String SESSION_COOKIE_NAME = "JSESSIONID";
    private final String SAME_SITE_ATTRIBUTE_VALUES = ";HttpOnly;Secure;SameSite=None";

    
    private static final String THIRD_PARTY_URI = "/third/party/uri";

    
	private TokenProvider tokenProvider;

	public JWTFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		//Para peticiones y validar mi jwt
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String jwt = resolveToken(httpServletRequest);
		String requestURI = httpServletRequest.getRequestURI();

		//Colocar la cokie segura sin aceptar conexiones externas se usa SameSite=strict
		HttpServletResponse resp = (HttpServletResponse) servletResponse;
		resp.setHeader("Set-Cookie", "HttpOnly; SameSite=None; Secure");
	
		if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			LOG.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), requestURI);
		} else {
			LOG.debug("no valid JWT token found, uri: {}", requestURI);
		}

		filterChain.doFilter(servletRequest, servletResponse);

	}

	// retorna el token completo
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
