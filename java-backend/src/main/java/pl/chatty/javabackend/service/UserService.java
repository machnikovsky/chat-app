package pl.chatty.javabackend.service;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.repository.UserRepository;
import pl.chatty.javabackend.service.mapper.UserMapper;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    public ResponseEntity<String> addUser(CreateUserRequest requestBody) {
        if (!(userRepository.existsByEmail(requestBody.getEmail())
                || userRepository.existsByUsername(requestBody.getUsername()))) {
            UserEntity toSave = mapper.mapToUserEntity(requestBody);
            userRepository.insert(toSave);
            return new ResponseEntity<>("User successfully saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
    }
}
