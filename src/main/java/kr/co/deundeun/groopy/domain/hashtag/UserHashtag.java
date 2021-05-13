package kr.co.deundeun.groopy.domain.hashtag;

import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.user.User;
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

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

}
