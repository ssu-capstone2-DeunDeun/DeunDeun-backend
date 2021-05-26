package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.ClubRecruitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClubRecruitRepository extends JpaRepository<ClubRecruit, Long> {
    List<ClubRecruit> findAllByClubAndGenerationGreaterThan(Club club, int id);
    ClubRecruit findTopByClubAndGenerationGreaterThanOrderByCreatedAt(Club club, int generation);
    List<ClubRecruit> findTop5ByClubRecruitStatusEqualsOrderBySubmitStartDateDesc(ClubRecruitStatus clubRecruitStatus);
    List<ClubRecruit> findTop5ByClubRecruitStatusEqualsOrderByLikeCountDesc(ClubRecruitStatus clubRecruitStatus);
    List<ClubRecruit> findAllByClubApplyFormId(Long applyFormId);

}
