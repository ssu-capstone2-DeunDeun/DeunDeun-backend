package kr.co.deundeun.groopy.controller.club.dto;

import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitResponseDto;
import kr.co.deundeun.groopy.controller.post.dto.PostResponseDto;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClubResponseDto {

    private Long id;

    private String clubName;

    private CategoryType categoryType;

    private int generation;

    private int likeCount;

    private String introduction;

    private String representImageUrl;

    private String backgroundImageUrl;

    private List<String> clubImageUrls;

    private List<PostResponseDto> postResponseDtos;

    private RecruitResponseDto recruitResponseDto;

    private boolean isAdmin;

    public static ClubResponseDto of(Club club, List<Post> posts, ClubRecruit clubRecruit, boolean isAdmin) {
        return ClubResponseDto.builder()
                .club(club)
                .posts(posts)
                .clubRecruit(clubRecruit)
                .isAdmin(isAdmin).build();
    }

    public static ClubResponseDto of(Club club) {
        return ClubResponseDto.builder()
                .club(club)
                .build();
    }

    public static List<ClubResponseDto> listOf(List<Club> clubs){
        return clubs.stream()
                .map(ClubResponseDto::of)
                .collect(Collectors.toList());
    }

    @Builder
    public ClubResponseDto(Club club, List<Post> posts, ClubRecruit clubRecruit, boolean isAdmin) {
        this.id = club.getId();
        this.clubName = club.getClubName();
        this.categoryType = club.getCategoryType();
        this.generation = club.getGeneration();
        this.introduction = club.getIntroduction();
        this.likeCount = club.getLikeCount();
        this.representImageUrl = club.getRepresentImageUrl();
        this.backgroundImageUrl = club.getBackgroundImageUrl();
        this.clubImageUrls = club.toImageUrls();
        this.postResponseDtos = PostResponseDto.listOf(posts);
        this.recruitResponseDto = RecruitResponseDto.of(clubRecruit);
        this.isAdmin = isAdmin;
    }
}
