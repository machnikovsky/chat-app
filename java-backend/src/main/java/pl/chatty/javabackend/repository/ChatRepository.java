package pl.chatty.javabackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.chatty.javabackend.model.dao.ChatEntity;

public interface ChatRepository extends MongoRepository<ChatEntity, String> {
}
