package com.macro.mall.tiny.component;

import com.macro.mall.tiny.common.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 * Created by macro on 2018/4/26.
 */
// OncePerRequestFilter是每次请求都会经过这个过滤器
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            // Bearer token是一种token
            String authToken = authHeader.substring(this.tokenHead.length());    // The part after "Bearer "
            String username = jwtTokenUtil.getUserNameFromToken(authToken);

            // 如果context中没有Authentication，则表示未登录
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // UserDetail包括authorities, username, password等参数
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // 将token（从request header中获取）和UserDetail（从数据库中获取）做对比
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {

                    // UsernamePasswordAuthenticationToken中包括principal(用户名), credentials(密码), authorities(授权)
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user:{}", username);

                    // 将authentication加入到context中
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
