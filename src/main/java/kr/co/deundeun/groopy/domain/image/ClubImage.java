package kr.co.deundeun.groopy.domain.image;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import kr.co.deundeun.groopy.domain.club.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ClubImage extends Image{
  @ManyToOne(fetch = FetchType.LAZY)
  private Club club;
}
