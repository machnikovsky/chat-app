package pl.chatty.javabackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.security.jwt.ChattyUserDetailsService;
import pl.chatty.javabackend.security.jwt.JwtAuthRequest;
import pl.chatty.javabackend.security.jwt.JwtAuthResponse;
import pl.chatty.javabackend.security.jwt.JwtUtil;
import pl.chatty.javabackend.service.UserService;
import pl.chatty.javabackend.service.UserServiceImpl;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000")
@Slf4j
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final ChattyUserDetailsService chattyUserDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;


    @PostMapping("/authenticate")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtAuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body("Invalid username or password, error message: " + e.getMessage());
        }

        UserDetails userDetails = chattyUserDetailsService.loadUserByUsername(authRequest.getUsername());
        String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtAuthResponse(jwtToken));
    }

    @PostMapping(path = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody CreateUserRequest requestBody){
        try {
            ResponseEntity<String> responseEntity = userService.addUser(requestBody);
            log.info(responseEntity.getBody());
            return responseEntity;
        } catch (HttpClientErrorException exception) {
            log.info(exception.toString());
            throw new ResponseStatusException(exception.getStatusCode(), exception.getMessage());
        }
    }
}