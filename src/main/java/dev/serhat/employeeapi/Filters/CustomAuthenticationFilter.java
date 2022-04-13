package dev.serhat.employeeapi.Filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import dev.serhat.employeeapi.core.utilities.results.ErrorResult;
import dev.serhat.employeeapi.core.utilities.results.SuccessDataResult;
import dev.serhat.employeeapi.security.JwtTokenProvider;
import dev.serhat.employeeapi.security.MyUserDetails;


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
 
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager,JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager= authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
                 
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password) ;
        
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authentication) throws IOException, ServletException {
                
        MyUserDetails user = ((MyUserDetails)authentication.getPrincipal());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = jwtTokenProvider.createAccessToken(user, request.getRequestURL().toString(), algorithm);
        String refresh_token = jwtTokenProvider.createRefreshToken(user, request.getRequestURL().toString(), algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        SuccessDataResult<Object> successDataResult = new SuccessDataResult<Object>(tokens,"User verified!");
        response.setContentType("application/json");

        new ObjectMapper().writeValue(response.getOutputStream(), successDataResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        
                response.setContentType("application/json");
                ErrorResult errorResult =new ErrorResult("Email or password is wrong!");
                response.setStatus(401);

                new ObjectMapper().writeValue(response.getOutputStream(), errorResult); 
    }
    

    
    
    
}
