package pl.chatty.javabackend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatParticipantsDTO {
    private ArrayList<String> chatParticipantsIds;
}
