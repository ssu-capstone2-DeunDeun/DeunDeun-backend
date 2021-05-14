package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.alarm.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
