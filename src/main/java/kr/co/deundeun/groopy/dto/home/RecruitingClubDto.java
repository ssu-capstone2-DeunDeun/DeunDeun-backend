package kr.co.deundeun.groopy.dto.home;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RecruitingClubDto {

  private Long clubRecruitId;
  private int generation;
  private String title;
  private String content;
  private String representImageUrl;
  private CategoryType categoryType;
  private String clubName;
  private boolean isRecruitingNow;
  private Long dDay;

  public RecruitingClubDto(ClubRecruit clubRecruit) {
    this.clubRecruitId = clubRecruit.getId();
    this.generation = clubRecruit.getRecruitGeneration();
    this.title = clubRecruit.getTitle();
    this.content = clubRecruit.getContent();
    Club club = clubRecruit.getClub();
    this.representImageUrl = club.getRepresentImageUrl();
    this.categoryType = club.getCategoryType();
    this.clubName = club.getClubName();
    this.isRecruitingNow = club.isRecruitingNow();
    this.dDay = club.getdDay();
  }

  public static List<RecruitingClubDto> listOf(List<ClubRecruit> clubRecruits) {
    if (clubRecruits == null){
      return new ArrayList<>();
    }
    return clubRecruits.stream()
                       .map(RecruitingClubDto::new)
                       .collect(Collectors.toList());
  }
}
