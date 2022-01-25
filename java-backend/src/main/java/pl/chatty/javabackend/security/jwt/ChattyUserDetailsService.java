package pl.chatty.javabackend.security.jwt;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.domains.user.repository.UserRepository;
import pl.chatty.javabackend.security.ChattyUserDetails;

@Service
public class ChattyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public ChattyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsernameIgnoreCase(username)
                .map(ChattyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
