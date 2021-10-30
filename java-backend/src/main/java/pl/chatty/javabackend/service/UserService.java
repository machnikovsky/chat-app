package pl.chatty.javabackend.service;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;
import pl.chatty.javabackend.service.mapper.UserMapper;

@Service
public class UserService {

    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    //TODO
    public void saveUser(CreateUserRequest userEntity) {
        UserEntity toSave = mapper.mapToUserEntity(userEntity);

    }

}
