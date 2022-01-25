package pl.chatty.javabackend.domains.image.service;



import lombok.AllArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.chatty.javabackend.domains.image.model.entity.ImageEntity;
import pl.chatty.javabackend.domains.image.repository.ImageRepository;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;


    public String addImage(MultipartFile multipartFile) throws IOException {
        ImageEntity image = new ImageEntity();
        image.setImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));

        image = imageRepository.insert(image);
        return image.getImageId();
    }

    public ImageEntity getImageByID(String id){
        return imageRepository.findById(id).get();
    }
}