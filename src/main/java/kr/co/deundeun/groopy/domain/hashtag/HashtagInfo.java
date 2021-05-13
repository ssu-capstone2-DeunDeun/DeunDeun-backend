package kr.co.deundeun.groopy.domain.hashtag;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Getter
@NoArgsConstructor
@Entity
public class HashtagInfo extends BaseEntity {

  @Column(nullable = false)
  private String name;
}
