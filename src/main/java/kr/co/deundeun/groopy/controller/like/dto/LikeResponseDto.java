package kr.co.deundeun.groopy.controller.like.dto;

import kr.co.deundeun.groopy.controller.club.dto.ClubResponseDto;
import kr.co.deundeun.groopy.controller.post.dto.PostResponseDto;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.like.ClubLike;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@Getter
public class LikeResponseDto {
    private boolean isLike = false;

    @Builder
    public LikeResponseDto(boolean isLike) {
        this.isLike = isLike;

    }

    public static LikeResponseDto of(ClubLike clubLike) {
        return LikeResponseDto.builder().isLike(clubLike.isLiked()).build();
    }


}
