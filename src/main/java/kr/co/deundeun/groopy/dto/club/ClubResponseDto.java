package kr.co.deundeun.groopy.dto.club;

import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import kr.co.deundeun.groopy.dto.hashtag.HashtagResponseDto;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClubResponseDto {

    private Long clubId;

    private CategoryType categoryType;

    private String clubName;

    private int generation = 0;

    private String introduction;

    private String representImageUrl;

    private String backgroundImageUrl;

    private int likeCount = 0;

    private List<String> clubImageUrls;

    private List<HashtagResponseDto> clubHashtags;

    private boolean isApproved;

    public static ClubResponseDto of(Club club) {
        return ClubResponseDto.builder()
                .club(club)
                .build();
    }

    public static List<ClubResponseDto> listOf(List<Club> clubs) {
        return clubs.stream()
                .map(ClubResponseDto::of)
                .collect(Collectors.toList());
    }


    @Builder
    public ClubResponseDto(Club club) {
        this.clubId = club.getId();
        this.clubName = club.getClubName();
        this.categoryType = club.getCategoryType();
        this.generation = club.getGeneration();
        this.introduction = club.getIntroduction();
        this.likeCount = club.getLikeCount();
        this.representImageUrl = club.getRepresentImageUrl();
        this.backgroundImageUrl = club.getBackgroundImageUrl();
        this.clubImageUrls = club.toImageUrls();
        this.clubHashtags = HashtagResponseDto.ofList(club.getClubHashtags().stream().map(ClubHashtag::getHashtagInfo).collect(Collectors.toList()));
        this.isApproved = club.isApproved();
    }


}
