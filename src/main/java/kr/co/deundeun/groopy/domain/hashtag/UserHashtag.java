package kr.co.deundeun.groopy.domain.hashtag;

import kr.co.deundeun.groopy.domain.user.UserHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@ToString
@Getter
@Entity
public class UserHashtag extends Hashtag {

    @ManyToOne
    private UserHistory userHistory;

}
