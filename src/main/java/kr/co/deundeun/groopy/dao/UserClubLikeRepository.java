package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.like.ClubLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClubLikeRepository extends JpaRepository<ClubLike, Long> {
}
