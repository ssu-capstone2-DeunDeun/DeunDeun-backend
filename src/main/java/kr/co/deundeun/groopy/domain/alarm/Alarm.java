package kr.co.deundeun.groopy.domain.alarm;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class Alarm extends BaseEntity {

    @ManyToOne
    private User user;

    private Long targetId;

    private String message;

    private boolean isRead;
}
