package pl.chatty.javabackend.domains.user.model.dto.response;

import lombok.*;
import org.bson.types.Binary;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class UserDTO {
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private Binary profileImage;
    private String email;
    private String phoneNumber;
}
