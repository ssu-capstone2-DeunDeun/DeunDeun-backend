package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
