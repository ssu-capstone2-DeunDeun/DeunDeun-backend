package kr.co.deundeun.groopy.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import kr.co.deundeun.groopy.dto.common.page.Property;
import kr.co.deundeun.groopy.dto.home.ClubByCategoryDto;
import kr.co.deundeun.groopy.dto.home.HomeResponseDto;
import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.PostRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.ClubRecruitStatus;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
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
        clubRecruits = clubRecruitRepository.findTop5ByClubRecruitStatusEqualsOrderByLikeCountDesc(ClubRecruitStatus.RECRUIT);
        //throw new BadRequestException("유효하지 않은 정렬 방식입니다.");
    }
    return clubRecruits;
  }

  @Transactional(readOnly = true)
  public List<ClubByCategoryDto> getAllClubs(User user, CategoryType categoryType) {
    List<Club> clubs;
    if (categoryType == null) { // null이 전체 조회
      clubs = clubRepository.findAll();
    } else {
      clubs = clubRepository.findAllByCategoryType(categoryType);
    }
    clubs.sort(Comparator.comparing(Club::getLikeCount));
    return ClubByCategoryDto.listOf(clubs, user);
  }

}
