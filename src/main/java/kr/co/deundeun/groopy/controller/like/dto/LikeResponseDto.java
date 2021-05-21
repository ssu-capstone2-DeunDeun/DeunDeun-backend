package kr.co.deundeun.groopy.controller.like.dto;

import kr.co.deundeun.groopy.domain.like.ClubLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LikeResponseDto {
    private boolean isLike = false;

    public static LikeResponseDto of(ClubLike clubLike){
        return new LikeResponseDto(clubLike.isLiked());
    }

}
