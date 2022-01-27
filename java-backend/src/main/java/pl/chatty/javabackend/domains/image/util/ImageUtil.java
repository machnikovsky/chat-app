package pl.chatty.javabackend.domains.image.util;

import pl.chatty.javabackend.domains.image.model.entity.ImageEntity;
import pl.chatty.javabackend.domains.image.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@AllArgsConstructor
@Component
public class ImageUtil {
    private final ImageRepository imageRepository;

    public Optional<ImageEntity> getImageByImageID(String imageId){
        return imageRepository.findById(imageId);
    }

    public ImageEntity saveImageInDatabase(ImageEntity image){
        return imageRepository.save(image);
    }
}
