package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubApplyRepository extends JpaRepository<ClubApply, Long> {
    List<ClubApply> findAllByUser(User user);

    ClubApply findByUserAndClubRecruitId(User user, Long clubRecruitId);
}
