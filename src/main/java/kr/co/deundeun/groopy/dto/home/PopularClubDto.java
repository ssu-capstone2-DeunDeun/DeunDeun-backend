package kr.co.deundeun.groopy.dto.home;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.club.Club;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PopularClubDto {

  private Long clubId;
  private String clubName;
  private String representImageUrl;
  private long dDay;


  public PopularClubDto(Club club) {
    this.clubId = club.getId();
    this.clubName = club.getClubName();
    this.representImageUrl = club.getRepresentImageUrl();
    this.dDay = club.getdDay();
  }

  public static List<PopularClubDto> listOf(List<Club> clubs){
    if (clubs == null){
      return new ArrayList<>();
    }
    return clubs.stream()
                .map(PopularClubDto::new)
                .collect(Collectors.toList());
  }
}
