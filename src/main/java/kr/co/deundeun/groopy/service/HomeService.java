package kr.co.deundeun.groopy.service;

import java.util.List;
import kr.co.deundeun.groopy.controller.common.page.Property;
import kr.co.deundeun.groopy.controller.home.dto.HomeResponseDto;
import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.PostRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.ClubRecruitStatus;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class HomeService {

  private final ClubRepository clubRepository;
  private final ClubRecruitRepository clubRecruitRepository;
  private final PostRepository postRepository;

  public HomeResponseDto getHome(Property property) {
    List<Club> clubs = clubRepository.findTop5ByOrderByLikeCountDesc();
    List<ClubRecruit> clubRecruits = getClubRecruits(property);
    List<Post> posts = postRepository.findTop4ByOrderByLikeCount();
    return new HomeResponseDto(clubs, clubRecruits, posts);
  }

  private List<ClubRecruit> getClubRecruits(Property property) {
    List<ClubRecruit> clubRecruits;
    switch (property) {
      case DATE:
        clubRecruits = clubRecruitRepository.findTop5ByClubRecruitStatusEqualsOrderBySubmitStartDateDesc(ClubRecruitStatus.RECRUIT);
        break;
      case APPLICANT:
        clubRecruits = clubRecruitRepository.findTop5ByClubRecruitStatusEqualsOrderByLikeCountDesc(ClubRecruitStatus.RECRUIT);
        break;
      default:
        throw new BadRequestException("유효하지 않은 정렬 방식입니다.");
    }
    return clubRecruits;
  }
}
