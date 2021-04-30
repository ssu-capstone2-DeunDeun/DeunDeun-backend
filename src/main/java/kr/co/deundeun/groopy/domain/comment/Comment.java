package kr.co.deundeun.groopy.domain.comment;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@Getter
@ToString
public abstract class Comment extends BaseEntity {

    private String comment;

    @ManyToOne
    private User author;



}
