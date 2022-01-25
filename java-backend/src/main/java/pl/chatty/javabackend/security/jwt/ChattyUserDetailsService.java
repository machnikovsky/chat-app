package pl.chatty.javabackend.security.jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.domains.user.repository.UserRepository;
import pl.chatty.javabackend.security.ChattyUserDetails;

@Service
@RequiredArgsConstructor
public class ChattyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsernameIgnoreCase(username)
                .map(ChattyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
