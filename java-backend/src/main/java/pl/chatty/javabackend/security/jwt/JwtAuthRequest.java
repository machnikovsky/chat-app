package pl.chatty.javabackend.security.jwt;

public class JwtAuthRequest {
    private final String username;
    private final String password;

    public JwtAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
