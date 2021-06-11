package js.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AddResponseHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal
            (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }
}
