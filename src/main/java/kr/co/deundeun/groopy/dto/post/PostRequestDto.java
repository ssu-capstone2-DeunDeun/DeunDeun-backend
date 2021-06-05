package kr.co.deundeun.groopy.dto.post;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.exception.BadRequestException;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PostRequestDto {

    private String title;

    private String content;

    private String thumbnailUrl;

    public Post toPost(String author, Club club){
        if (author == null || author.isEmpty()) throw new BadRequestException("동아리에 등록된 인원이 아닙니다.");

        return Post.builder()
                .postRequestDto(this)
                .author(author)
                .club(club)
                .build();
    }
}
