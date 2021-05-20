package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.like.ClubLike;
import kr.co.deundeun.groopy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubLikeRepository extends JpaRepository<ClubLike, Long> {
    ClubLike findByClubAndUser(Club club, User user);
    boolean existsByClubAndUser(Club club, User user);
}
