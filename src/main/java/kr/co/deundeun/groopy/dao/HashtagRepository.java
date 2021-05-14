package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.hashtag.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
