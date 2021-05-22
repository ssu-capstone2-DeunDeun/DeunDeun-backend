package kr.co.deundeun.groopy.controller.user.dto;

import kr.co.deundeun.groopy.controller.club.dto.ClubResponseDto;
import kr.co.deundeun.groopy.controller.like.dto.LikeResponseDto;
import kr.co.deundeun.groopy.controller.post.dto.PostResponseDto;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class LikeListResponseDto {

    private List<ClubResponseDto> clubResponseDtos;

    private List<PostResponseDto> postResponseDtos;

    public static LikeListResponseDto of(List<Club> clubs, List<Post> posts) {
        return new LikeListResponseDto(ClubResponseDto.listOf(clubs), PostResponseDto.listOf(posts));
    }
}
