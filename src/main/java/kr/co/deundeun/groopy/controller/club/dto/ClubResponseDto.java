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
public class ClubResponseDto {

    private Long id;

    private String clubName;

    private CategoryType categoryType;

    private int generation;

    private String introduction;

    private String representImageUrl;

    private String backgroundImageUrl;

    private List<String> clubImageUrls;

    private List<PostResponseDto> postResponseDtos;

    private RecruitResponseDto recruitResponseDto;

    private boolean isAdmin;

    public static ClubResponseDto of(Club club, List<Post> posts, ClubRecruit clubRecruit, boolean isAdmin) {
        return ClubResponseDto.builder()
                .id(club.getId())
                .clubName(club.getClubName())
                .categoryType(club.getCategoryType())
                .generation(club.getGeneration())
                .introduction(club.getIntroduction())
                .representImageUrl(club.getRepresentImageUrl())
                .backgroundImageUrl(club.getBackgroundImageUrl())
                .clubImageUrls(club.toImageUrls())
                .postResponseDtos(PostResponseDto.listOf(posts))
                .recruitResponseDto(RecruitResponseDto.of(clubRecruit))
                .isAdmin(isAdmin).build();
    }

    @Builder
    public ClubResponseDto(Long id, String clubName, CategoryType categoryType, int generation,
                           String introduction, String representImageUrl, String backgroundImageUrl,
                           List<String> clubImageUrls, List<PostResponseDto> postResponseDtos,
                           RecruitResponseDto recruitResponseDto, boolean isAdmin) {
        this.id = id;
        this.clubName = clubName;
        this.categoryType = categoryType;
        this.generation = generation;
        this.introduction = introduction;
        this.representImageUrl = representImageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.clubImageUrls = clubImageUrls;
        this.postResponseDtos = postResponseDtos;
        this.recruitResponseDto = recruitResponseDto;
        this.isAdmin = isAdmin;
    }
}
