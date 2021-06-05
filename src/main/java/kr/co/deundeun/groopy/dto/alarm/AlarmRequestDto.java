package kr.co.deundeun.groopy.dto.alarm;

import kr.co.deundeun.groopy.domain.alarm.Alarm;
import kr.co.deundeun.groopy.domain.alarm.constant.AlarmType;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AlarmRequestDto {

    private String receiver;

    private AlarmType alarmType;

    private String message;

    public Alarm toAlarm(){
        return Alarm.builder().alarmRequestDto(this).build();
    }
}
