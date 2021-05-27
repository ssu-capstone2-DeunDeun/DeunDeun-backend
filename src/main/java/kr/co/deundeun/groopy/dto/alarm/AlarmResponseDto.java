package kr.co.deundeun.groopy.dto.alarm;

import kr.co.deundeun.groopy.domain.alarm.Alarm;
import kr.co.deundeun.groopy.domain.alarm.constant.AlarmType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AlarmResponseDto {

    private AlarmType alarmType;
    private String message;

    public AlarmResponseDto(Alarm alarm){
        this.alarmType = alarm.getAlarmType();
        this.message = alarm.getMessage();
    }
}
