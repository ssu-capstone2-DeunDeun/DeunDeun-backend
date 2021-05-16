package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRecruitRepository extends JpaRepository<ClubRecruit, Long> {
    List<ClubRecruit> findAllByClub(Club club);
}
