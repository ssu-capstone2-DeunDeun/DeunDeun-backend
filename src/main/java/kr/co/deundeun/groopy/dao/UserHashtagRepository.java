package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import kr.co.deundeun.groopy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHashtagRepository extends JpaRepository<UserHashtag, Long> {
    List<UserHashtag> findAllByUser(User user);

    void deleteAllByUser(User user);
}
