package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.user.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
}
