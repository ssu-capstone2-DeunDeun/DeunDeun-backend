package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.ClubRecruitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRecruitRepository extends JpaRepository<ClubRecruit, Long> {
    List<ClubRecruit> findAllByClub(Club club);

    ClubRecruit findTopByClubOrderByCreatedAt(Club club);

    List<ClubRecruit> findTop5ByClubRecruitStatusEqualsOrderBySubmitStartDateDesc(ClubRecruitStatus clubRecruitStatus);

    List<ClubRecruit> findTop5ByClubRecruitStatusEqualsOrderByLikeCountDesc(ClubRecruitStatus clubRecruitStatus);

    List<ClubRecruit> findAllByClubApplyFormId(Long applyFormId);

    List<ClubRecruit> findAllByClubRecruitStatusIsNot(ClubRecruitStatus clubRecruitStatus);
}
