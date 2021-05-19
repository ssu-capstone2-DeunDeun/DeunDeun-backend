package kr.co.deundeun.groopy.domain.image;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import kr.co.deundeun.groopy.domain.club.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
public class ClubImage extends Image{
  @ManyToOne(fetch = FetchType.LAZY)
  private Club club;

  public ClubImage(String imageUrl){
    this.imageUrl = imageUrl;
  }

  public void setClub(Club club){
    if(this.club != null)
      this.club.getClubImages().remove(this);

    this.club = club;
    club.getClubImages().add(this);
  }

  public static List<ClubImage> ofList(List<String> imageUrls){
    return imageUrls.stream()
            .map(ClubImage::new)
            .collect(Collectors.toList());
  }
}
