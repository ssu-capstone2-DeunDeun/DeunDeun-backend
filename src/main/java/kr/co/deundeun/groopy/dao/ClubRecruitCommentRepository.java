package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.comment.ClubRecruitComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRecruitCommentRepository extends JpaRepository<ClubRecruitComment, Long> {
}
