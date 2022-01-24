package pl.chatty.javabackend.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtAuthRequest {
    private String username;
    private String password;
}
