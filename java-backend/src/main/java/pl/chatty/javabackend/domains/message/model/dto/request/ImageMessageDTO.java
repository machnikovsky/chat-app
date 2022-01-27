package pl.chatty.javabackend.domains.message.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ImageMessageDTO extends MessageDTO{
    private String imageContent;
}
