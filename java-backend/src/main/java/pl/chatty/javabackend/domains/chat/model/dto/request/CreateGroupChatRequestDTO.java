package pl.chatty.javabackend.domains.chat.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateGroupChatRequestDTO {
    private ArrayList<String> groupParticipantsIds;
    private String groupName;
}
