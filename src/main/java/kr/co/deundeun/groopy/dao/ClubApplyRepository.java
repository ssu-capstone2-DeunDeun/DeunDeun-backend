package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ClubApplyRepository extends JpaRepository<ClubApply, Long> {
    List<ClubApply> findAllByUser(User user);

    List<ClubApply> findAllByClubRecruit_Id(Long id);

    List<ClubApply> findAllByIdIn(Collection<Long> id);

    Page<ClubApply> findAllByClubRecruit_Club(Club club, Pageable pageable);
}
