package kr.co.deundeun.groopy.domain.like;

import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@ToString
public abstract class Like extends BaseEntity {

    private boolean isLiked;

}
