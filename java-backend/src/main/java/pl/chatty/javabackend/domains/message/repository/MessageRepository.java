package pl.chatty.javabackend.domains.message.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.chatty.javabackend.domains.message.model.entity.MessageEntity;

@Repository
public interface MessageRepository extends MongoRepository<MessageEntity, String> {
}
