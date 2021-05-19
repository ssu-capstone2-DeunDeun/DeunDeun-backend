package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.image.ClubImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubImageRepository extends JpaRepository<ClubImage, Long> {
    List<ClubImage> findAllByClub(Club club);
}
