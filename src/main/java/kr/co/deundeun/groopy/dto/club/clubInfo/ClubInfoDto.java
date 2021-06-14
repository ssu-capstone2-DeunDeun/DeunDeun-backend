package kr.co.deundeun.groopy.dto.club.clubInfo;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.dto.club.ClubResponseDto;
import kr.co.deundeun.groopy.dto.clubRecruit.ClubRecruitResponseDto;
import kr.co.deundeun.groopy.dto.post.PostResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ClubInfoDto {

    private ClubResponseDto clubResponseDto;

    private List<PostResponseDto> postResponseDtos;

    private List<ClubRecruitResponseDto> clubRecruitResponseDtos;

    private boolean isAdmin;

    public ClubInfoDto(Club club, List<Post> posts, List<ClubRecruit> clubRecruits, boolean isAdmin) {
        this.clubResponseDto = ClubResponseDto.of(club);
        this.postResponseDtos = PostResponseDto.listOf(posts);
        this.clubRecruitResponseDtos = ClubRecruitResponseDto.listOf(clubRecruits);
        this.isAdmin = isAdmin;
    }
}
