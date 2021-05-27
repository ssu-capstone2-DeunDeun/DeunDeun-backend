package kr.co.deundeun.groopy.dto.home;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Getter;

import java.util.List;

@Getter

public class HomeResponseDto {

    private List<PopularClubDto> popularClubDtos;
    private List<RecruitingClubDto> recruitingClubDtos;
    private List<PopularPostDto> popularPostDtos;

    public HomeResponseDto(List<Club> clubs, List<ClubRecruit> clubRecruits, List<Post> posts) {
        this.popularClubDtos = PopularClubDto.listOf(clubs);
        this.recruitingClubDtos = RecruitingClubDto.listOf(clubRecruits);
        this.popularPostDtos = PopularPostDto.listOf(posts);
    }
}
