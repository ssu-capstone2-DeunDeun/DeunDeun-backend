package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.hashtag.HashtagInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagInfoRepository extends JpaRepository<HashtagInfo, Long> {
    Optional<HashtagInfo> findByName(String name);
}
