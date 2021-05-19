package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClubRecruitRepository extends JpaRepository<ClubRecruit, Long> {
    List<ClubRecruit> findAllByClub(Club club);
    ClubRecruit findTopByClubAndGenerationGreaterThanOrderByCreatedAt(Club club, int generation);

}
