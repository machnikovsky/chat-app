package pl.chatty.javabackend.service;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.model.dto.response.UsersResponse;
import pl.chatty.javabackend.repository.UserRepository;
import pl.chatty.javabackend.service.mapper.UserMapper;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

    public UsersResponse getUsers(Pageable paging) {
        UsersResponse allPosts = new UsersResponse(userRepository
                .findAllOrderByUserIdDesc(paging).getContent());
        return allPosts;
    }


}
