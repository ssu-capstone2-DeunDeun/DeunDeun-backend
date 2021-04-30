package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.like.UserClubLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClubLikeRepository extends JpaRepository<UserClubLike, Long> {
}
