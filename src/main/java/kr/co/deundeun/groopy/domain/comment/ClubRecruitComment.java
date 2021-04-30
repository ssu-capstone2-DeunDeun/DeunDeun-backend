package kr.co.deundeun.groopy.domain.comment;

import kr.co.deundeun.groopy.domain.club.ClubRecruit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ClubRecruitComment extends Comment{

    @ManyToOne
    private ClubRecruit clubRecruit;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubRecruitComment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<ClubRecruitComment> childComment;

}
