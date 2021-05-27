package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.clubApplyForm.ClubRecruitQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRecruitQuestionRepository extends JpaRepository<ClubRecruitQuestion, Long> {
}
