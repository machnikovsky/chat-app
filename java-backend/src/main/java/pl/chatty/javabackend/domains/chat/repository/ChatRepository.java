package pl.chatty.javabackend.domains.chat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.chatty.javabackend.domains.chat.model.entity.ChatEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, String> {
    Optional<ChatEntity> findByChatId(String chatId);
    List<ChatEntity> findAllByMembersIdsContaining(String userId);

    @Query("{ 'membersIds' : ?0 }")
    Optional<ChatEntity> findByChatParticipants(List<String> participantsIds);
}
