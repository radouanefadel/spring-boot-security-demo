package io.relfadel.springboot_security_demo.users.data.repositories;

import io.relfadel.springboot_security_demo.users.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(final String email);
}
