package kr.co.deundeun.groopy.domain.clubRecruit;

import javax.persistence.FetchType;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.image.ClubRecruitImage;
import lombok.Builder;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    private int generation;

    private String content;

    @OneToMany(mappedBy = "clubRecruit")
    private List<ClubRecruitImage> clubRecruitImages;

    @OneToMany(mappedBy = "clubRecruit")
    private List<Comment> comments;

    @OneToMany(mappedBy = "clubRecruit")
    private List<ClubRecruitQuestion> clubRecruitQuestions;

    @Builder
    public ClubRecruit(Club club, int generation, List<ClubRecruitImage> clubRecruitImages,
                       List<Comment> comments, List<ClubRecruitQuestion> clubRecruitQuestions){
        this.club = club;
        this.generation = generation;
        this.clubRecruitImages = clubRecruitImages;
        this.comments = comments;
        this.clubRecruitQuestions = clubRecruitQuestions;
    }

}
