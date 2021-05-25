package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.alarm.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findAllByUserId(Long userId);
}
