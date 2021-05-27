package kr.co.deundeun.groopy.domain.club;

import java.util.Comparator;

import kr.co.deundeun.groopy.dto.club.ClubRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.ClubRecruitStatus;
import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.exception.ClubRecruitNotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Entity
public class Club extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(nullable = false, unique = true)
    private String clubName;

    private int generation;

    private String introduction;

    private String representImageUrl;

    private String backgroundImageUrl;

    private int likeCount = 0;

    @OneToMany(mappedBy = "club")
    private List<ClubHashtag> clubHashtags = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<ClubImage> clubImages = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Post> clubPosts = new ArrayList<>();

    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "club")
    private List<ClubRecruit> clubRecruits = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<ClubApplyForm> clubApplyForms = new ArrayList<>();

    private boolean isApproved;

    @Builder
    public Club(CategoryType categoryType, String clubName, int generation, String introduction,
                String representImageUrl, String backgroundImageUrl, boolean isApproved,
                List<ClubHashtag> clubHashtags, List<ClubImage> clubImages, List<Post> clubPosts,
                List<ClubRecruit> clubRecruits) {
        this.categoryType = categoryType;
        this.clubName = clubName;
        this.generation = generation;
        this.introduction = introduction;
        this.representImageUrl = representImageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.clubHashtags = clubHashtags;
        this.clubImages = clubImages;
        this.clubPosts = clubPosts;
        this.clubRecruits = clubRecruits;
        this.isApproved = isApproved;
    }

    public Club update(ClubRequestDto clubRequestDto) {
        this.clubName = clubRequestDto.getName();
        this.introduction = clubRequestDto.getIntroduction();
        this.representImageUrl = clubRequestDto.getRepresentImageUrl();
        return this;
    }

    public void setClubImage(ClubImage clubImage) {
        if (clubImages.stream().anyMatch(image -> image.toImageUrl()
                .equals(clubImage.getImageUrl()))) return;
        this.clubImages.add(clubImage);
        clubImage.setClub(this);
    }

    public void setClubImages(List<ClubImage> clubImages) {
        if (this.clubImages == null) clubImages = new ArrayList<>();
        clubImages.forEach(this::setClubImage);
    }

    public List<String> toImageUrls() {
        return this.clubImages.stream()
                .map(ClubImage::toImageUrl)
                .collect(Collectors.toList());
    }

    public void increaseLikeCount() {
        likeCount++;
    }

    public void decreaseLikeCount() {
        if (likeCount > 0) likeCount--;
        else likeCount = 0;
    }

    public ClubRecruit getLastClubRecruit() {
        return this.clubRecruits.stream()
                .max(Comparator.comparing(ClubRecruit::getGeneration))
                .orElseThrow(ClubRecruitNotFoundException::new);
    }

    public boolean isRecruitingNow() {
        return this.getLastClubRecruit()
                   .getClubRecruitStatus()
                   .equals(ClubRecruitStatus.RECRUIT);
    }

    public void setApproved(boolean isApproved){
        this.isApproved = isApproved;
    }
}
