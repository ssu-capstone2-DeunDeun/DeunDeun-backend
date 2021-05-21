package kr.co.deundeun.groopy.controller.post.dto;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.image.ClubImage;
import kr.co.deundeun.groopy.domain.image.PostImage;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.exception.BadRequestException;
import lombok.Getter;

import java.util.List;

@Getter
public class PostRequestDto {

    private String title;

    private String content;

    private List<String> postImageUrls;

    public Post toPost(String author, Club club){
        if (author == null || author.isEmpty()) throw new BadRequestException("동아리에 등록된 인원이 아닙니다.");

        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .club(club)
                .build();
    }
}
