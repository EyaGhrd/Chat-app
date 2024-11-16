package tn.rnu.eniso.fwk.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.rnu.eniso.fwk.chatapp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
