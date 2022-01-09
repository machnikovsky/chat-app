package pl.chatty.javabackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.chatty.javabackend.model.dao.ChatEntity;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, String> {
}
