package pl.chatty.javabackend.domains.image.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.Binary;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ImageDTO {
    private String imageID;
    private Binary image;
}