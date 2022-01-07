package pl.chatty.javabackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@PropertySource("classpath:application.properties")
@Configuration
public class AppConfig {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void firebaseApp() throws IOException {
        InputStream serviceAccount = getClass()
                .getClassLoader()
                .getResourceAsStream(environment.getRequiredProperty("chatapp.firebaseKey"));


        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(environment.getRequiredProperty("chatapp.databaseUrl"))
                .build();

        FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseDatabase firebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Bean
    public FirebaseAuth firebaseAuth() {
        return FirebaseAuth.getInstance();
    }
}
