package kr.co.deundeun.groopy.controller.like.dto;

import kr.co.deundeun.groopy.domain.club.ClubLike;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
