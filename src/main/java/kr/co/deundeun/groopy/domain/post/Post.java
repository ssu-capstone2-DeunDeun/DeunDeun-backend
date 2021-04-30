package kr.co.deundeun.groopy.domain.post;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
@Getter
public abstract class Post extends BaseEntity {

    private String title;

    private String content;

    @OneToOne
    private User author;
}
