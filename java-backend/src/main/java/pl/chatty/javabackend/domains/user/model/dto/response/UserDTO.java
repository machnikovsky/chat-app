package pl.chatty.javabackend.domains.user.model.dto.response;

import lombok.*;

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
}
