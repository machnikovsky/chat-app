package pl.chatty.javabackend.domains.message.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TextMessageDTO extends MessageDTO{
    private String messageContent;
}
