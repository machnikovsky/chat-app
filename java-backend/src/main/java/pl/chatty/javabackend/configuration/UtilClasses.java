package pl.chatty.javabackend.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UtilClasses {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
