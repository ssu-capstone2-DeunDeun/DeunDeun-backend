package kr.co.deundeun.groopy.domain.club;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
public class ClubImage extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    private String imageUrl;

    public ClubImage(Club club, String imageUrl) {
        this();
        setClub(club);
        this.imageUrl = imageUrl;
    }

    public void setClub(Club club) {
        if (this.club != null) {
            this.club.getClubImages().remove(this);
        }
        this.club = club;
        club.getClubImages().add(this);
    }

    public static List<ClubImage> ofList(Club club, List<String> imageUrls) {
        return imageUrls.stream()
                .map(imageUrl -> new ClubImage(club, imageUrl))
                .collect(Collectors.toList());
    }

    public String toImageUrl() {
        return imageUrl;
    }
}
