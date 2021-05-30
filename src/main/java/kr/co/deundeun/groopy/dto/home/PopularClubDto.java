package kr.co.deundeun.groopy.dto.home;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;

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
    this.dDay = getdDay(club);
  }

  public static List<PopularClubDto> listOf(List<Club> clubs){
    if (clubs == null){
      return new ArrayList<>();
    }
    return clubs.stream()
                .map(PopularClubDto::new)
                .collect(Collectors.toList());
  }

  public long getdDay(Club club){
    if(club.isRecruitingNow()) {
      ClubRecruit clubRecruit = club.getLastClubRecruit();
      dDay = Duration.between(LocalDateTime.now(), clubRecruit.getSubmitEndDate()).toDays();
    }
    else
      dDay = -1;
      return dDay;
  }
}
