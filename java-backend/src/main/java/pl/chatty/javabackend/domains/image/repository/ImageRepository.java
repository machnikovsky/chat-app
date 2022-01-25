package pl.chatty.javabackend.domains.image.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.chatty.javabackend.domains.image.model.entity.ImageEntity;

@Repository
public interface ImageRepository extends MongoRepository<ImageEntity, String> { }

