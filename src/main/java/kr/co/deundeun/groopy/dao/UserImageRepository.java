package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.image.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
}
