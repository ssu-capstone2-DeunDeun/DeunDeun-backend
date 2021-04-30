package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.image.ClubImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubImageRepository extends JpaRepository<ClubImage, Long> {
}
