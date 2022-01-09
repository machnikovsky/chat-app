package pl.chatty.javabackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.chatty.javabackend.model.dao.UserEntity;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    UserEntity getUserEntityByUserId(String userId);

    Page<UserEntity> findAllOrderByUserIdDesc(Pageable paging);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);
}
