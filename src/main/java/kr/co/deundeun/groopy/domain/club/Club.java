package kr.co.deundeun.groopy.domain.club;

import kr.co.deundeun.groopy.controller.club.dto.ClubRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import kr.co.deundeun.groopy.domain.image.ClubImage;
import kr.co.deundeun.groopy.domain.image.Image;
import kr.co.deundeun.groopy.domain.post.Post;
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

    @OneToMany(mappedBy = "club")
    private List<ClubHashtag> clubHashtags;

    @OneToMany(mappedBy = "club")
    private List<ClubImage> clubImages;

    @OneToMany(mappedBy = "club")
    private List<Post> clubPosts;

    @OneToMany(mappedBy = "club")
    private List<ClubRecruit> clubRecruits;

    @Builder
    public Club(CategoryType categoryType, String clubName, int generation, String introduction,
                String representImageUrl, String backgroundImageUrl, List<ClubHashtag> clubHashtags,
                List<ClubImage> clubImages, List<Post> clubPosts, List<ClubRecruit> clubRecruits){

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
    }

    public Club update(ClubRequestDto clubRequestDto){
        this.clubName = clubRequestDto.getName();
        this.introduction = clubRequestDto.getIntroduction();
        this.representImageUrl = clubRequestDto.getRepresentImageUrl();
        return this;
    }

    public void setClubImage(ClubImage clubImage){
        if(clubImages.stream().anyMatch(image -> image.toImageUrl().equals(clubImage.getImageUrl()))) return;
        this.clubImages.add(clubImage);
        clubImage.setClub(this);
    }

    public void setClubImages(List<ClubImage> clubImages){
        if(this.clubImages == null) this.clubImages = new ArrayList<>();
        clubImages.forEach(clubImage -> setClubImage(clubImage));
    }

    public List<String> toImageUrls(){
        return this.clubImages.stream()
                .map(Image::toImageUrl)
                .collect(Collectors.toList());
    }

}
