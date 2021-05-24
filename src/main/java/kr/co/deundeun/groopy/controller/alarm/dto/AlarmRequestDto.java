package kr.co.deundeun.groopy.controller.alarm.dto;

import kr.co.deundeun.groopy.domain.alarm.Alarm;
import kr.co.deundeun.groopy.domain.alarm.constant.AlarmType;
import lombok.Getter;

@Getter
public class AlarmRequestDto {

    private String receiver;

    private AlarmType alarmType;

    private String message;

    public Alarm toAlarm(){
        return Alarm.builder().alarmRequestDto(this).build();
    }
}
