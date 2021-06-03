package kr.co.deundeun.groopy.dto.like;

import kr.co.deundeun.groopy.domain.club.ClubLike;
import kr.co.deundeun.groopy.domain.post.PostLike;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LikeResponseDto {

    private Long userId;

    private String target;

    private Long targetId;

    private boolean isLike = false;

    public LikeResponseDto(User user, PostLike postLike) {
        this.userId = user.getId();
        this.target = "게시글";
        this.targetId = postLike.getPost().getId();
        this.isLike = postLike.isLiked();
    }

    public LikeResponseDto(User user, ClubLike clubLike) {
        this.userId = user.getId();
        this.target = "동아리";
        this.targetId = clubLike.getClub().getId();
        this.isLike = clubLike.isLiked();
    }


}
