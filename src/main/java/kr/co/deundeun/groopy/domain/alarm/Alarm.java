package kr.co.deundeun.groopy.domain.alarm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import kr.co.deundeun.groopy.controller.alarm.dto.AlarmRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.alarm.constant.AlarmType;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Alarm extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlarmType alarmType;

    @Column(nullable = false)
    private String targetUrl;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean isRead;

    @Builder
    public Alarm(User user, AlarmRequestDto alarmRequestDto){
        this.userId = user.getId();
        this.alarmType = alarmRequestDto.getAlarmType();
        this.message = alarmRequestDto.getMessage();
    }
}
