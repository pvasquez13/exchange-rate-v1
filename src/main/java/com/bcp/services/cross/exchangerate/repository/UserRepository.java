package com.bcp.services.cross.exchangerate.repository;

import com.bcp.services.cross.exchangerate.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  public Optional<User> findByEmail(String email);

  public Optional<User> findByUsernameOrEmail(String username, String email);

  public Optional<User> findByUsername(String username);

  public boolean existsByUsername(String username);

  public boolean existsByEmail(String email);
}
