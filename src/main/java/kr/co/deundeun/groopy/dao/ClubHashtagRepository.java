package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubHashtagRepository extends JpaRepository<ClubHashtag, Long> {
}
