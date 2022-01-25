package pl.chatty.javabackend.domains.chat.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.chatty.javabackend.domains.user.model.dto.response.UserDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ChatDTO {
    private String chatId;
    private String name;
    private List<UserDTO> users;
}


