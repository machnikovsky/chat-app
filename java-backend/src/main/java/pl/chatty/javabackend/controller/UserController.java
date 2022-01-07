package pl.chatty.javabackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.model.dto.response.UsersResponse;
import pl.chatty.javabackend.service.UserServiceImpl;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> json){
        return new ResponseEntity<>("Successful logged", HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody CreateUserRequest requestBody){
        try {
            ResponseEntity<String> responseEntity = userService.addUser(requestBody);
            log.info(responseEntity.getBody());
            return responseEntity;
        } catch (HttpClientErrorException exception) {
            log.info(exception.toString());
            throw new ResponseStatusException(exception.getStatusCode(), exception.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable("userId") String userId) {
        try {
            ResponseEntity<String> responseEntity = userService.removeUser(userId);
            log.info(responseEntity.getBody());
            return responseEntity;
        } catch (HttpClientErrorException exception) {
            log.info(exception.toString());
            throw new ResponseStatusException(exception.getStatusCode(), exception.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") String userId,
                                             @RequestBody CreateUserRequest requestBody) {
        try {
            ResponseEntity<String> responseEntity = userService.updateUser(userId, requestBody);
            log.info(responseEntity.getBody());
            return responseEntity;
        } catch (HttpClientErrorException exception) {
            log.info(exception.toString());
            throw new ResponseStatusException(exception.getStatusCode(), exception.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("userId") String userId) {
        try {
            ResponseEntity<UserEntity> responseEntity = userService.getUser(userId);
            log.info(String.valueOf(responseEntity.getBody()));
            return responseEntity;
        } catch (HttpClientErrorException exception) {
            log.info(exception.toString());
            throw new ResponseStatusException(exception.getStatusCode(), exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<UsersResponse> getUsers(@RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
            try {
                Pageable paging = PageRequest.of(page, size);
                UsersResponse usersResponse = userService.getUsers(paging);
                log.info(String.valueOf(usersResponse.getUsers()));
                return new ResponseEntity<>(usersResponse, HttpStatus.OK);
            } catch (HttpClientErrorException exception) {
                log.info(exception.toString());
                throw new ResponseStatusException(exception.getStatusCode(), exception.getMessage());
            }
    }

    @PutMapping("/{userID}/password")
    public ResponseEntity<String> updatePassword(@PathVariable int userID, @RequestBody Map<String, String> json) {
        return new ResponseEntity<>("Password successful updated", HttpStatus.OK);
    }

    @PostMapping("/{userID}/friend/{friendID}")
    public ResponseEntity<String> addUserToFriends(@PathVariable int userID,
                                                   @PathVariable int friendID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("Successful added to friends", HttpStatus.OK);
    }

    @DeleteMapping ("/{userID}/friend/{friendID}")
    public ResponseEntity<String> removeUserFromFriends(@PathVariable int userID,
                                                        @PathVariable int friendID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("Successful removed friend", HttpStatus.OK);
    }

    @PostMapping("/{userID}/friend/{friendID}/nickname")
    public ResponseEntity<String> changeFriendsUsername(@PathVariable int userID,
                                                        @PathVariable int friendID, @RequestBody Map<String, String> json){
        return new ResponseEntity<>("User's nickname successfully changed", HttpStatus.OK);
    }
}
