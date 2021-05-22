package kr.co.deundeun.groopy.domain.image;

import javax.persistence.FetchType;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
public class ClubRecruitImage extends Image {
    @ManyToOne(fetch = FetchType.LAZY)
    private ClubRecruit clubRecruit;

    public ClubRecruitImage(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void setClubRecruit(ClubRecruit clubRecruit) {
        if (this.clubRecruit != null)
            this.clubRecruit.getClubRecruitImages().remove(this);
        this.clubRecruit = clubRecruit;
        clubRecruit.getClubRecruitImages().add(this);
    }

    public static List<ClubRecruitImage> ofList(List<String> recruitImageUrls){
        return recruitImageUrls.stream()
                .map(ClubRecruitImage::new)
                .collect(Collectors.toList());
    }
}
