package com.iauto.eschool.config.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenVerifyFilter extends OncePerRequestFilter{

    private List<String> excludeUrlPatterns = new ArrayList<String>(Arrays.asList("/swagger-ui.html",
            "/swagger-uui.html", "/webjars/springfox-swagger-ui/springfox.css",
            "/webjars/springfox-swagger-ui/swagger-ui-bundle.js", "/webjars/springfox-swagger-ui/swagger-ui.css",
            "/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js",
            "/webjars/springfox-swagger-ui/springfox.js", "/swagger-resources/configuration/ui",
            "/webjars/springfox-swagger-ui/favicon-32x32.png", "/swagger-resources/configuration/security",
            "/swagger-resources", "/v2/api-docs",
            "/webjars/springfox-swagger-ui/fonts/titillium-web-v6-latin-700.woff2",
            "/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-regular.woff2",
            "/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-700.woff2",
            "/webjars/springfox-swagger-ui/favicon-16x16.png",
            "swagger-ui/*"));
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		if(Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authorizationHeader.replace("Bearer ", "");
		String secretKey ="abcddfdsf1243abcddfdsf1243abcddfdsf1243";
		/*
		Jwts.parserBuilder()
			.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
			.se
		*/
		Jws<Claims> claimsJws = Jwts.parser()
			.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
			.parseClaimsJws(token);
		
		Claims body = claimsJws.getBody();
		String username = body.getSubject();
		List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
		
		Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
			.map(x -> new SimpleGrantedAuthority(x.get("authority")))
			.collect(Collectors.toSet());
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		
	}
	
	//https://stackoverflow.com/questions/62900796/swagger-is-not-opening-after-adding-onceperrequestfilter-is-spring-boot-applicat
	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        if (excludeUrlPatterns.contains(path)) {
            return true;
        } else {
            return false;
        }
    }
   
   

}
