package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.post.PostLike;
import kr.co.deundeun.groopy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    List<PostLike> findAllByUser(User user);
}
