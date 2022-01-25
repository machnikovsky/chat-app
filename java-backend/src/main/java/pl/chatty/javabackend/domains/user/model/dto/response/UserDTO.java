package pl.chatty.javabackend.domains.user.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDTO {
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
}
