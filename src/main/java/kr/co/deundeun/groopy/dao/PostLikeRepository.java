package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.like.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
