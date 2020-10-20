package com.cplier.forum.repository;

import com.cplier.forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ehcayen
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
