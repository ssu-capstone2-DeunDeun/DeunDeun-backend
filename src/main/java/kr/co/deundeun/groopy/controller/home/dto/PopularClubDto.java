package kr.co.deundeun.groopy.controller.home.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.club.Club;
import lombok.Getter;

@Getter
public class PopularClubDto {

  private Long clubId;
  private String clubName;
  private boolean isRecruitingNow;
  private String representImageUrl;

  public PopularClubDto(Club club) {
    this.clubId = club.getId();
    this.clubName = club.getClubName();
    this.isRecruitingNow = club.isRecruitingNow();
    this.representImageUrl = club.getRepresentImageUrl();
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
