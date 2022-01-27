package pl.chatty.javabackend.domains.image.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("images")
@Data
@NoArgsConstructor
public class ImageEntity {

    @Id
    private String imageId;
    private Binary image;

    public ImageEntity(Binary image) {
        this.image = image;
    }
}