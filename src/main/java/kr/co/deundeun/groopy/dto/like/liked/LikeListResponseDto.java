package kr.co.deundeun.groopy.dto.like.liked;

import kr.co.deundeun.groopy.dto.club.ClubResponseDto;
import kr.co.deundeun.groopy.dto.post.PostResponseDto;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
public class LikeListResponseDto {

    private List<ClubResponseDto> clubResponseDtos;

    private List<PostResponseDto> postResponseDtos;

    public static LikeListResponseDto of(List<Club> clubs, List<Post> posts) {
        return new LikeListResponseDto(ClubResponseDto.listOf(clubs), PostResponseDto.listOf(posts));
    }
}
