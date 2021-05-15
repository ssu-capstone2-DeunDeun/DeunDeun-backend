package kr.co.deundeun.groopy.controller.club.dto;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClubResponseDto {

    private String clubName;

    private CategoryType categoryType;

    private int generation;

    private String introduction;

    private String representImageUrl;

    private String backgroundImageUrl;

    //private List<Post> clubPosts;

    //private List<ClubRecruit> clubRecruits;

    public static ClubResponseDto of(Club club){
        return new ClubResponseDto(club.getClubName(), club.getCategoryType(),
                club.getGeneration(), club.getIntroduction(), club.getRepresentImageUrl(),
                club.getBackgroundImageUrl());
    }

}
