package pl.chatty.javabackend;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.chatty.javabackend.model.Gender;
import pl.chatty.javabackend.model.UsersRole;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JavaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBackendApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowCredentials(true).allowedOrigins("http://localhost:3000").allowedMethods("*");
            }
        };
    }
}

@Component
@AllArgsConstructor
class ApplicationStartup {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MongoTemplate mongoTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void addUsers() {

        mongoTemplate.getDb().drop();

        userRepository.saveAll(List.of(
                new UserEntity(
                        "userOne",
                        "John",
                        "Doe",
                        "john@doe.com",
                        passwordEncoder.encode("password"),
                        "123123123",
                        Gender.MALE,
                        UsersRole.USER,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new UserEntity(
                        "userTwo",
                        "Jane",
                        "Doe",
                        "jane@doe.com",
                        passwordEncoder.encode("password"),
                        "123123123",
                        Gender.FAMALE,
                        UsersRole.USER,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new UserEntity(
                        "userThree",
                        "Joe",
                        "Doe",
                        "joe@doe.com",
                        passwordEncoder.encode("password"),
                        "456345234",
                        Gender.OTHER,
                        UsersRole.USER,
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        ));

    }
}