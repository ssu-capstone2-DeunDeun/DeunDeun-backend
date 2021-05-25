package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.alarm.dto.AlarmRequestDto;
import kr.co.deundeun.groopy.controller.alarm.dto.AlarmResponseDto;
import kr.co.deundeun.groopy.dao.AlarmRepository;
import kr.co.deundeun.groopy.domain.alarm.Alarm;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AlarmService {

    private final AlarmRepository alarmRepository;

    public void sendAlarm(User user, AlarmRequestDto alarmRequestDto) {
        Alarm alarm = alarmRequestDto.toAlarm();

        alarmRepository.save(alarm);
    }

    public List<AlarmResponseDto> getAlarm(User user) {
        List<Alarm> alarms = alarmRepository.findAllByUserId(user.getId());
        return alarms.stream().map(AlarmResponseDto::of).collect(Collectors.toList());
    }
}
