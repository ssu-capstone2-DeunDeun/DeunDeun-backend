package kr.co.deundeun.groopy.controller.post.dto;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostRequestDto {

    private String title;

    private String content;

    private List<String> postImageUrls;

    public Post toPost(String author, Club club){
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .club(club)
                .build();
    }
}
