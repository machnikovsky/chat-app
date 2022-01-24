package pl.chatty.javabackend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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


