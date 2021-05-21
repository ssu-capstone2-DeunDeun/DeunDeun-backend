package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruitQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRecruitQuestionRepository extends JpaRepository<ClubRecruitQuestion, Long> {
    List<ClubRecruitQuestion> findByClubRecruit(ClubRecruit clubRecruit);
    void deleteAllByClubRecruit(ClubRecruit clubRecruit);
}
