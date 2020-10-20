package com.cplier.forum.repository;

import com.cplier.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** @author ehcayen */
public interface UserRepository extends JpaRepository<User, Long> {
  /**
   * 根据用户名查找
   *
   * @param username 用户名
   * @return {@link Optional<User>}
   */
  Optional<User> findByUsername(String username);
}
