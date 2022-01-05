package pl.chatty.javabackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.chatty.javabackend.model.dao.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
