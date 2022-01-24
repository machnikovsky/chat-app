package pl.chatty.javabackend.service;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.exception.exceptions.UserEntityNotFoundException;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.model.dto.response.UserDTO;
import pl.chatty.javabackend.model.dto.response.UsersListDto;
import pl.chatty.javabackend.repository.UserRepository;
import pl.chatty.javabackend.service.mapper.UserMapper;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<String> addUser(CreateUserRequest requestBody) {
        if (!(userRepository.existsByEmail(requestBody.getEmail())
                || userRepository.existsByUsername(requestBody.getUsername()))) {
            UserEntity toSave = mapper.mapToUserEntity(requestBody, passwordEncoder);
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

    public UsersListDto getUsers(Pageable paging) {
        return new UsersListDto(
                userRepository.findAllByOrderByUserId(paging).getContent()
                        .stream()
                        .map(x -> modelMapper.map(x, UserDTO.class))
                        .collect(Collectors.toList())
        );
    }

    public UsersListDto getUsersBesideSelf() {
        String loggedInUser = getCurrentUserUsername()
                .orElseThrow(() -> new UserEntityNotFoundException(""));
        return new UsersListDto(
                userRepository.findAll()
                        .stream()
                        .filter(x -> !loggedInUser.equals(x.getUsername()))
                        .map(x -> modelMapper.map(x, UserDTO.class))
                        .collect(Collectors.toList())
        );
    }

    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public Optional<UserEntity> getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    public Optional<String> getCurrentUserUsername() {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            if ("anonymousUser".equals(name)) {
                return Optional.empty();
            } else {
                return Optional.of(name);
            }
    }

    public Optional<UserEntity> getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if ("anonymousUser".equals(name)) {
            return Optional.empty();
        } else {
            return userRepository.findByUsernameIgnoreCase(name);
        }
    }
}
