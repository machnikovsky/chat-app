package pl.chatty.javabackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.model.dto.response.UsersListDto;
import pl.chatty.javabackend.service.UserServiceImpl;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;


    @DeleteMapping(path = "/{userId}")
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

    @PutMapping(path =  "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(path = "/{userId}")
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

    @GetMapping(path = "/all")
    public ResponseEntity<UsersListDto> getUsers(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                 @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
            try {
                Pageable paging = PageRequest.of(page, size);
                UsersListDto usersListDto = userService.getUsers(paging);
                log.info(String.valueOf(usersListDto.getUsers()));
                return new ResponseEntity<>(usersListDto, HttpStatus.OK);
            } catch (HttpClientErrorException exception) {
                log.info(exception.toString());
                throw new ResponseStatusException(exception.getStatusCode(), exception.getMessage());
            }
    }

    @GetMapping("/all/others")
    public ResponseEntity<UsersListDto> getUsersBesideSelf() {
            return new ResponseEntity<>(userService.getUsersBesideSelf(), HttpStatus.OK);
    }

    @PutMapping("/{userID}/password")
    public ResponseEntity<String> updatePassword(@PathVariable int userID, @RequestBody Map<String, String> json) {
        return new ResponseEntity<>("Password successful updated", HttpStatus.OK);
    }

    @PostMapping("/{userID}/friend/{friendID}")
    public ResponseEntity<String> addUserToFriends(@PathVariable String userID,
                                                   @PathVariable String friendID){
        return null;
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
