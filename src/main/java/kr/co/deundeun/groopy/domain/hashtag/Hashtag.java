package kr.co.deundeun.groopy.domain.hashtag;

import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@ToString
public abstract class Hashtag extends BaseEntity {

    @Column(nullable = false)
    private String name;
}
