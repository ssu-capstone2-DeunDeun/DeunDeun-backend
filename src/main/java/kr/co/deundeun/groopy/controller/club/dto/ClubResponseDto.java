package kr.co.deundeun.groopy.controller.club.dto;

import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitResponseDto;
import kr.co.deundeun.groopy.controller.post.dto.PostResponseDto;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubResponseDto {

    private String clubName;

    private CategoryType categoryType;

    private int generation;

    private String introduction;

    private String representImageUrl;

    private String backgroundImageUrl;

    private List<String> clubImages;

    private List<PostResponseDto> posts;

    private RecruitResponseDto recruit;

    private boolean isAdmin;

    public static ClubResponseDto of(Club club, List<PostResponseDto> postResponseDtos,
                                     RecruitResponseDto recruit, List<String> clubImages, boolean isAdmin) {
        return ClubResponseDto.builder()
                .clubName(club.getClubName())
                .categoryType(club.getCategoryType())
                .generation(club.getGeneration())
                .introduction(club.getIntroduction())
                .representImageUrl(club.getRepresentImageUrl())
                .backgroundImageUrl(club.getBackgroundImageUrl())
                .clubImages(clubImages)
                .posts(postResponseDtos)
                .recruit(recruit)
                .isAdmin(isAdmin).build();
    }

}
