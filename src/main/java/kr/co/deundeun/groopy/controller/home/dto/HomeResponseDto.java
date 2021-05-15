package kr.co.deundeun.groopy.controller.home.dto;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeResponseDto {

    private List<Club> popularClubs;
    private List<Club> recruitingClubs;
    private List<Post> popularPosts;

}
