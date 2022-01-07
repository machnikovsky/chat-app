package pl.chatty.javabackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.chatty.javabackend.model.dao.MessageEntity;

public interface MessageRepository extends MongoRepository<MessageEntity, String> {
}
