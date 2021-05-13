package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruitQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRecruitQuestionRepository extends JpaRepository<ClubRecruitQuestion, Long> {
}
