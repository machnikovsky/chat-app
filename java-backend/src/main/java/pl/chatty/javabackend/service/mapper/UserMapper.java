package pl.chatty.javabackend.service.mapper;

import org.mapstruct.Mapper;
import pl.chatty.javabackend.model.dao.UserEntity;
import pl.chatty.javabackend.model.dto.request.CreateUserRequest;

@Mapper
public interface UserMapper {

    UserEntity mapToUserEntity(CreateUserRequest createUserRequest);

}
