package pl.chatty.javabackend.domains.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.chatty.javabackend.domains.user.model.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity getUserEntityByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    UserEntity getUserEntityByUserId(String userId);

    Page<UserEntity> findAllByOrderByUserId(Pageable paging);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    Optional<UserEntity> findByUserId(String userId);
}
