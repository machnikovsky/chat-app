package pl.chatty.javabackend.domains.user.util;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.chatty.javabackend.domains.user.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.domains.user.model.dto.request.UpdatePasswordRequest;
import pl.chatty.javabackend.domains.user.model.dto.response.UserDTO;
import pl.chatty.javabackend.domains.user.model.dto.response.UsersListDto;
import pl.chatty.javabackend.domains.user.model.entity.UserEntity;
import pl.chatty.javabackend.domains.user.repository.UserRepository;
import pl.chatty.javabackend.exception.exceptions.UserEntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserUtils {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserUtils(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public Optional<UserEntity> getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    public boolean addUser(CreateUserRequest requestBody) {
        if (!(userRepository.existsByEmail(requestBody.getEmail())
                || userRepository.existsByUsername(requestBody.getUsername()))) {
            UserEntity toSave = mapper.mapToUserEntity(requestBody, passwordEncoder);
            userRepository.insert(toSave);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeUser(String userId) {
        if(userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUser(String userId, CreateUserRequest requestBody) {
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

            if (requestBody.getPhoneNumber() != null)
                userEntity.setPhoneNumber(requestBody.getPhoneNumber());

            if (requestBody.getGender() != null)
                userEntity.setGender(requestBody.getGender());

            userRepository.save(userEntity);

            return true;
        } else {
            return false;
        }
    }

    public boolean updateUserPassword(String userId, UpdatePasswordRequest requestBody) {
        if (userRepository.existsById(userId)) {
            UserEntity user = userRepository.getUserEntityByUserId(userId);
            user.setPassword(passwordEncoder.encode(requestBody.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
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

    public UsersListDto getUsers(Pageable paging) {
        return new UsersListDto(
                userRepository.findAllByOrderByUserId(paging).getContent()
                        .stream()
                        .map(x -> modelMapper.map(x, UserDTO.class))
                        .collect(Collectors.toList())
        );
    }

    public List<UserDTO> mapUsersToUsersDTO(List<UserEntity> users) {
        return users.stream().map(x -> modelMapper.map(x, UserDTO.class)).collect(Collectors.toList());
    }
}
