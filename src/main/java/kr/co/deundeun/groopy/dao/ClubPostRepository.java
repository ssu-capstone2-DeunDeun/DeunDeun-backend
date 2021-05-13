package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.club.ClubPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubPostRepository extends JpaRepository<ClubPost, Long> {
}
