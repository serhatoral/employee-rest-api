package dev.serhat.employeeapi.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.serhat.employeeapi.core.utilities.results.DataResult;
import dev.serhat.employeeapi.models.User;
import dev.serhat.employeeapi.security.JwtTokenProvider;
import dev.serhat.employeeapi.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {
    
    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user){

        return ResponseEntity.ok(userService.addUser(user));  
    }

    @GetMapping("/getUser")
    public DataResult<User> getUserByEmail(@RequestParam String email){

        return userService.getUserByEmail(email);
    }



    @GetMapping("/token/refresh")
    public void resfreshToken(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
            
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                
                UserDetails user = jwtTokenProvider.verifyAndDecodeToken(algorithm, refresh_token);
                String access_token = jwtTokenProvider.createAccessToken(user, request.getRequestURL().toString(), algorithm);
                
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                    
                
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(403);
                response.sendError(403);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
            
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    } 




















    
    /* @GetMapping("/token/refresh")
    public void resfreshToken(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
            
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                DataResult<User> user = userService.getUserByEmail(username);
                
                
                String access_token = JWT.create()
                .withSubject(user.getData().getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getData().getRoles().stream().map(Role:: getRoleName).collect(Collectors.toList()))
                .sign(algorithm);
                
    
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                    
                
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(403);
                response.sendError(403);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
            
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

            
        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }  */
}
