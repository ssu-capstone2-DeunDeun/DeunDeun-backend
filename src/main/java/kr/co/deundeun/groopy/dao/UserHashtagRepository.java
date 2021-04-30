package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHashtagRepository extends JpaRepository<UserHashtag, Long> {
}
