package kr.co.deundeun.groopy.dto.home;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;

@Getter
public class RecruitingClubDto {

  private Long clubRecruitId;
  private int generation;
  private String title;
  private String content;
  private String representImageUrl;
  private CategoryType categoryType;
  private boolean isRecruitingNow;

  public RecruitingClubDto(ClubRecruit clubRecruit) {
    this.clubRecruitId = clubRecruit.getId();
    this.generation = clubRecruit.getRecruitGeneration();
    this.title = clubRecruit.getTitle();
    this.content = clubRecruit.getContent();
    Club club = clubRecruit.getClub();
    this.representImageUrl = club.getRepresentImageUrl();
    this.categoryType = club.getCategoryType();
    this.isRecruitingNow = club.isRecruitingNow();
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
