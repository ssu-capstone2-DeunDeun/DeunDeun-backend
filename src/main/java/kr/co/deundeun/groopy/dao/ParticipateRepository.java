package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipateRepository extends JpaRepository<Participate, Long> {
    List<Participate> findAllByUser(User user);
    List<Participate> findAllByClubRecruit_Club(Club club);
}
