package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
