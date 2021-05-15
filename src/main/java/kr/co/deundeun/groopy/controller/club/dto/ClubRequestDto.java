package kr.co.deundeun.groopy.controller.club.dto;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import kr.co.deundeun.groopy.domain.image.ClubImage;
import lombok.Getter;

import java.util.List;

@Getter
public class ClubRequestDto {
    private CategoryType categoryType;
    private String name;
    private String introduction;
    private List<String> clubHashtags;
    private String representImageUrl;
    //private List<ClubImage> introductionImageUrls;

    public Club toClub(){
        return Club.builder()
                .clubName(name)
                .categoryType(categoryType)
                .introduction(introduction)
                //.clubHashtags(clubHashtags)
                .representImageUrl(representImageUrl)
                //.clubImages(introductionImageUrls)
                .build();
    }
 }
