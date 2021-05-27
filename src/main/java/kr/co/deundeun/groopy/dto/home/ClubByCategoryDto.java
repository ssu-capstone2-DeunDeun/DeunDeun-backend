package kr.co.deundeun.groopy.dto.home;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;

@Getter
public class ClubByCategoryDto {

  private Long clubId;
  private String clubName;
  private String introduction;
  private String representClubImageUrl;
  private CategoryType categoryType;
  private List<String> hashtagNames;
  private boolean isLiked;
  private boolean isRecruiting;

  public ClubByCategoryDto(Club club, User user) {
    this.clubId = club.getId();
    this.clubName = club.getClubName();
    this.introduction = club.getIntroduction();
    this.representClubImageUrl = club.getRepresentImageUrl();
    this.categoryType = club.getCategoryType();
    this.hashtagNames = club.getClubHashtags()
                            .stream()
                            .map(ClubHashtag::getHashtagName)
                            .collect(Collectors.toList());
    this.isLiked = user.isUserLikeClub(club);
    this.isRecruiting = club.isRecruitingNow();
  }

  public static List<ClubByCategoryDto> listOf(List<Club> clubs, User user) {
    return clubs.stream()
                .map(club -> new ClubByCategoryDto(club, user))
                .collect(Collectors.toList());
  }
}
