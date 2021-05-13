package kr.co.deundeun.groopy.domain.hashtag;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Entity
public abstract class Hashtag extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  private HashtagInfo hashtagInfo;
}
