package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.image.ClubRecruitImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRecruitImageRepository extends JpaRepository<ClubRecruitImage, Long> {
}
