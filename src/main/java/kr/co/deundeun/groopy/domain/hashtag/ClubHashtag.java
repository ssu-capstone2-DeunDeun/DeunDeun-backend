package kr.co.deundeun.groopy.domain.hashtag;

import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.club.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@NoArgsConstructor
@Entity
public class ClubHashtag extends Hashtag {

  @ManyToOne(fetch = FetchType.LAZY)
  private Club club;
}
