package kr.co.deundeun.groopy.domain.club;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.comment.ClubRecruitComment;
import kr.co.deundeun.groopy.domain.image.ClubRecruitImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ClubRecruit extends BaseEntity {

    @ManyToOne
    private Club club;

    private int generation;

    private String content;

    @OneToMany(mappedBy = "clubRecruit")
    private List<ClubRecruitImage> clubRecruitImages;

    @OneToMany(mappedBy = "clubRecruit")
    private List<ClubApply> clubApplys;

    @OneToMany(mappedBy = "clubRecruit")
    private List<ClubRecruitComment> clubRecruitComments;

    @OneToMany(mappedBy = "clubRecruit")
    private List<ClubRecruitQuestion> clubRecruitQuestions;

}
