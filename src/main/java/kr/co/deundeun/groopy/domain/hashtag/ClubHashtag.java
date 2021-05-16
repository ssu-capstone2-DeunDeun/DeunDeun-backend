package kr.co.deundeun.groopy.domain.hashtag;

import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@NoArgsConstructor
@Entity
public class ClubHashtag extends Hashtag {

  @ManyToOne(fetch = FetchType.LAZY)
  private Club club;

  @Builder
  public ClubHashtag(Club club, HashtagInfo hashtagInfo){
    this.club = club;
    this.hashtagInfo = hashtagInfo;
  }
}
