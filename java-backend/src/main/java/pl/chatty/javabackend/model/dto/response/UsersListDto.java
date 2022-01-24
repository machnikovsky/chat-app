package pl.chatty.javabackend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.chatty.javabackend.model.dao.UserEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UsersListDto {

    List<UserEntity> users;

}
