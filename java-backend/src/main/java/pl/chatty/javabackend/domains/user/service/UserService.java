package pl.chatty.javabackend.domains.user.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.domains.user.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.domains.user.model.dto.response.UsersListDto;
import pl.chatty.javabackend.domains.user.model.entity.UserEntity;
import pl.chatty.javabackend.domains.user.repository.UserRepository;
import pl.chatty.javabackend.domains.user.util.UserUtils;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserUtils userUtils;


    public ResponseEntity<String> addUser(CreateUserRequest requestBody) {
        if (userUtils.addUser(requestBody)) {
            return new ResponseEntity<>("User successfully saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<String> removeUser(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateUser(String userId, CreateUserRequest requestBody) {
        if (userRepository.existsById(userId)) {
            UserEntity userEntity = userRepository.getUserEntityByUserId(userId);

            if (requestBody.getUsername() != null)
                userEntity.setUsername(requestBody.getUsername());

            if (requestBody.getEmail() != null)
                userEntity.setEmail(requestBody.getEmail());

            if (requestBody.getFirstName() != null)
                userEntity.setFirstName(requestBody.getFirstName());

            if (requestBody.getLastName() != null)
                userEntity.setLastName(requestBody.getLastName());

            if (requestBody.getPassword() != null)
                userEntity.setPassword(requestBody.getPassword());

            if (requestBody.getPhoneNumber() != null)
                userEntity.setPhoneNumber(requestBody.getPhoneNumber());

            if (requestBody.getGender() != null)
                userEntity.setGender(requestBody.getGender());

            userRepository.save(userEntity);

            return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UserEntity> getUser(String userId) {
        if (userRepository.existsById(userId)) {
            return new ResponseEntity<>(userRepository.getUserEntityByUserId(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UsersListDto> getUsersBesideSelf() {
        return ResponseEntity.ok(userUtils.getUsersBesideSelf());
    }
}
