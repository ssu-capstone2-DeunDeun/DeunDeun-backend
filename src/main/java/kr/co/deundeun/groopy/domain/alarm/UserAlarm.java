package kr.co.deundeun.groopy.domain.alarm;

import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class UserAlarm extends Alarm {

    @ManyToOne
    private User user;

}
