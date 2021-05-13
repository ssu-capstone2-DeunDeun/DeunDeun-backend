package kr.co.deundeun.groopy.domain.user;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.alarm.UserAlarm;
import kr.co.deundeun.groopy.domain.club.ClubApply;
import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import kr.co.deundeun.groopy.domain.like.UserClubLike;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "userHistory")
    private List<UserHashtag> userHashtagList;

    @OneToMany(mappedBy = "userHistory")
    private List<UserAlarm> userAlarmList;

    @OneToMany(mappedBy = "userHistory")
    private List<UserClub> userClubList;

    @OneToMany(mappedBy = "userHistory")
    private List<UserClubLike> userClubLikeList;

    @OneToMany(mappedBy = "userHistory")
    private List<ClubApply> clubApplyList;

}
