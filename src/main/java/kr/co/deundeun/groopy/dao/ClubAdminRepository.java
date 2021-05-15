package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubAdminRepository extends JpaRepository<ClubAdmin, Long> {
    boolean existsByUserIdAndClub(Long userId, Club club);
}
