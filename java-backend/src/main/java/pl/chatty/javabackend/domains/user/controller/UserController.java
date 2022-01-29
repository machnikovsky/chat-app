package pl.chatty.javabackend.domains.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import pl.chatty.javabackend.domains.image.model.dto.request.ImageDTO;
import pl.chatty.javabackend.domains.user.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.domains.user.model.dto.request.UpdatePasswordRequest;
import pl.chatty.javabackend.domains.user.model.dto.response.UserDTO;
import pl.chatty.javabackend.domains.user.model.dto.response.UsersListDto;
import pl.chatty.javabackend.domains.user.model.entity.UserEntity;
import pl.chatty.javabackend.domains.user.service.UserService;
import pl.chatty.javabackend.domains.user.util.UserUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserUtils userUtils;

    @GetMapping("/all/others")
    public ResponseEntity<UsersListDto> getUsersBesideSelf() {
        return userService.getUsersBesideSelf();
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable("userId") String userId) {
        return userService.removeUser(userId);
    }

    @PutMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable("userId") String userId,
                                             @RequestBody CreateUserRequest requestBody) {
        return userService.updateUser(userId, requestBody);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<UsersListDto> getUsers(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                 @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return userService.getUsers(page, size);
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<String> updatePassword(@PathVariable String userId, @RequestBody UpdatePasswordRequest requestBody) {
        return userService.updatePassword(userId, requestBody);
    }

    @PostMapping("/query/{query}")
    public ResponseEntity<List<UserDTO>> getUsersByQuery(@PathVariable("query") String query) {
        return userService.getUsersByQuery(query);
    }


    @PostMapping("/{userID}/friend/{friendID}")
    public ResponseEntity<String> addUserToFriends(@PathVariable String userID,
                                                   @PathVariable String friendID) {
        return null;
    }

    @DeleteMapping("/{userID}/friend/{friendID}")
    public ResponseEntity<String> removeUserFromFriends(@PathVariable int userID,
                                                        @PathVariable int friendID, @RequestBody Map<String, String> json) {
        return new ResponseEntity<>("Successful removed friend", HttpStatus.OK);
    }

    @PostMapping("/{userID}/friend/{friendID}/nickname")
    public ResponseEntity<String> changeFriendsUsername(@PathVariable int userID,
                                                        @PathVariable int friendID, @RequestBody Map<String, String> json) {
        return new ResponseEntity<>("User's nickname successfully changed", HttpStatus.OK);
    }

    @PostMapping("/profilepicture")
    public ResponseEntity<String> setUserProfileImage(@RequestBody ImageDTO imageDTO) {
        try {
            Optional<UserEntity> user = userUtils.getCurrentUser();
            CreateUserRequest userRequest = new CreateUserRequest();
            userService.updateUser(user.get().getUserId(), null);
        } catch (HttpClientErrorException exception) {
            log.info(exception.toString());
            throw new ResponseStatusException(exception.getStatusCode(), exception.getMessage());
        }
        return new ResponseEntity<>("User's profile image successfully changed", HttpStatus.OK);
    }
}
