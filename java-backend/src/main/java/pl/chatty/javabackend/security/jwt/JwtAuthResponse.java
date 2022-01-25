package pl.chatty.javabackend.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtAuthResponse {
    private final String jwt;
}
