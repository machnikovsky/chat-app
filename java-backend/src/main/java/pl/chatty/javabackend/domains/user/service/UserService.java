package pl.chatty.javabackend.domains.user.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.chatty.javabackend.domains.user.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.domains.user.model.dto.request.UpdatePasswordRequest;
import pl.chatty.javabackend.domains.user.model.dto.response.UserDTO;
import pl.chatty.javabackend.domains.user.model.dto.response.UsersListDto;
import pl.chatty.javabackend.domains.user.model.entity.UserEntity;
import pl.chatty.javabackend.domains.user.repository.UserRepository;
import pl.chatty.javabackend.domains.user.util.UserUtils;
import pl.chatty.javabackend.exception.exceptions.UserEntityNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
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
        if (userUtils.removeUser(userId)) {
            return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateUser(String userId, CreateUserRequest requestBody) {
        if (userUtils.updateUser(userId, requestBody)) {
            return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updatePassword(String userId, UpdatePasswordRequest requestBody) {
        if (userUtils.updateUserPassword(userId, requestBody)) {
            return new ResponseEntity<>("Password successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UsersListDto> getUsers(int page, int size) {
        if (page >= 0 && size > 0) {
            Pageable paging = PageRequest.of(page, size);
            return new ResponseEntity<>(userUtils.getUsers(paging), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UserEntity> getUser(String userId) {
        if (userRepository.existsById(userId)) {
            return new ResponseEntity<>(userRepository.getUserEntityByUserId(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(() ->
                new UserEntityNotFoundException("User not found"));
    }

    public ResponseEntity<UsersListDto> getUsersBesideSelf() {
        return ResponseEntity.ok(userUtils.getUsersBesideSelf());
    }

    @Async("asyncTaskExecutor")
    public CompletableFuture<ResponseEntity<List<UserDTO>>> getUsersByQuery(String query) {
        log.info("Getting users by query using thread: {}", Thread.currentThread());
        String loggedInUser = userUtils.getCurrentUserUsername()
                .orElseThrow(() -> new UserEntityNotFoundException("currently logged in user"));
        List<UserEntity> users = userRepository.findAllByUsernameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                query, query, query
        ).stream().filter(x -> !loggedInUser.equals(x.getUsername())).collect(Collectors.toList());

        return CompletableFuture.completedFuture(ResponseEntity.ok(userUtils.mapUsersToUsersDTO(users)));
    }

    public ResponseEntity<String> setUserProfileImage(MultipartFile file) throws IOException {
        Binary image = new Binary(BsonBinarySubType.BINARY, file.getBytes());

        UserEntity user = userUtils.getCurrentUser()
                .orElseThrow(() -> new UserEntityNotFoundException("Current user"));

        user.setProfileImage(image);
        userUtils.saveUserInDatabase(user);

        return ResponseEntity.ok("User's profile image is updated");
    }
}
