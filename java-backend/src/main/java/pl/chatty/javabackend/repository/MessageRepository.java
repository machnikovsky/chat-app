package pl.chatty.javabackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.chatty.javabackend.model.dao.MessageEntity;

@Repository
public interface MessageRepository extends MongoRepository<MessageEntity, String> {
}
