package pl.chatty.javabackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.chatty.javabackend.model.dao.ChatEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, String> {
    boolean existsByChatId(String chatId);
    Optional<ChatEntity> findByChatId(String chatId);
    List<ChatEntity> findAllByMembersIdsContaining(String userId);

    @Query("{ 'membersIds' : ?0 }")
    Optional<ChatEntity> findByChatParticipants(List<String> participantsIds);
}
