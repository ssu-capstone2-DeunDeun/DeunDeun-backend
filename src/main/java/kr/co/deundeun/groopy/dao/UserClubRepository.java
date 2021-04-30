package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.user.UserClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClubRepository extends JpaRepository<UserClub, Long> {
}
