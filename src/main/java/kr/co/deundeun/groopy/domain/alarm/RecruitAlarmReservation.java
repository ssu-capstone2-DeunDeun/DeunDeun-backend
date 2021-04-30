package kr.co.deundeun.groopy.domain.alarm;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.ClubRecruit;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class RecruitAlarmReservation extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private ClubRecruit clubRecruit;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    @Enumerated(EnumType.STRING)
    private ReceivingType receivingType;

}
