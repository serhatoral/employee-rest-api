package dev.serhat.employeeapi.security;

import java.util.Date;
import java.util.stream.Collectors;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import dev.serhat.employeeapi.service.MyUserDetailsService;

@Configuration
public class JwtTokenProvider{
    
    private final MyUserDetailsService myUserDetailsService;
    @Autowired
    public JwtTokenProvider(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    public UserDetails verifyAndDecodeToken (Algorithm algorithm, String token){

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        UserDetails user = myUserDetailsService.loadUserByUsername(username);

        return user;
    }


    public String createAccessToken (UserDetails user, String requestUrl,Algorithm algorithm){

        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                .withIssuer(requestUrl)
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        return access_token;
    }


    public String createRefreshToken (UserDetails user, String requestUrl, Algorithm algorithm){

        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                .withIssuer(requestUrl)
                .sign(algorithm);

        return refresh_token;
    }

}
