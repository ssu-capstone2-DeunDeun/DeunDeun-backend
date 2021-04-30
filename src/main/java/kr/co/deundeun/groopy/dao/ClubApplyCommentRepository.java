package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.comment.ClubApplyComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubApplyCommentRepository extends JpaRepository<ClubApplyComment, Long> {
}
