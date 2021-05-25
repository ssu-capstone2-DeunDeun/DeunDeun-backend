package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubPositionRepository extends JpaRepository<ClubPosition, Long> {
    ClubPosition findByClubAndPositionName(Club club, String positionName);
}
