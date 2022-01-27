package pl.chatty.javabackend.domains.user.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import pl.chatty.javabackend.domains.user.model.dto.response.UserDTO;
import pl.chatty.javabackend.domains.user.model.entity.UserEntity;
import pl.chatty.javabackend.domains.user.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.domains.user.model.dto.response.UsersListDto;
import pl.chatty.javabackend.domains.user.service.UserService;
import pl.chatty.javabackend.domains.user.util.UserUtils;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserUtils userUtils;

    @GetMapping("/all/others")
    public ResponseEntity<UsersListDto> getUsersBesideSelf() {
        return userService.getUsersBesideSelf();
    }

    //TODO: Move these to service/utils
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

    //TODO: Move these to service/utils
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

    //TODO: Move these to service/utils
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

    //TODO: Move these to service/utils
    @GetMapping(path = "/all")
    public ResponseEntity<UsersListDto> getUsers(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                 @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
            try {
                Pageable paging = PageRequest.of(page, size);
                UsersListDto usersListDto = userUtils.getUsers(paging);
                log.info(String.valueOf(usersListDto.getUsers()));
                return new ResponseEntity<>(usersListDto, HttpStatus.OK);
            } catch (HttpClientErrorException exception) {
                log.info(exception.toString());
                throw new ResponseStatusException(exception.getStatusCode(), exception.getMessage());
            }
    }

    @PostMapping("/query/{query}")
    public ResponseEntity<List<UserDTO>> getUsersByQuery(@PathVariable("query") String query) {
        return userService.getUsersByQuery(query);
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
