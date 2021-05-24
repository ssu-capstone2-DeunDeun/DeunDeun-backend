package kr.co.deundeun.groopy.controller.alarm;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.controller.alarm.dto.AlarmRequestDto;
import kr.co.deundeun.groopy.controller.alarm.dto.AlarmResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AlarmController {

    private final AlarmService alarmService;

    @PostMapping("/alarms")
    public void sendAlarm(@Me User user, @RequestBody AlarmRequestDto alarmRequestDto){
        alarmService.sendAlarm(user, alarmRequestDto);
    }

    @GetMapping("/alarms")
    public ResponseEntity<List<AlarmResponseDto>> getAlarm(@Me User user){
        return ResponseEntity.ok(alarmService.getAlarm(user));
    }



}
