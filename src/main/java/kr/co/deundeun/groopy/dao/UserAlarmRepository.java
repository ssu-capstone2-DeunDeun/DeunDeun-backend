package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.alarm.UserAlarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAlarmRepository extends JpaRepository<UserAlarm, Long> {
}
