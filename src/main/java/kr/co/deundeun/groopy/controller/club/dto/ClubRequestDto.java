package kr.co.deundeun.groopy.controller.club.dto;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import lombok.Getter;

import java.util.List;

@Getter
public class ClubRequestDto {
    private CategoryType categoryType;
    private String name;
    private String introduction;
    private List<String> clubHashtags;
    private String representImageUrl;
    private List<String> clubImages;

    public Club toClub(){
        return Club.builder()
                .clubName(name)
                .categoryType(categoryType)
                .introduction(introduction)
                .representImageUrl(representImageUrl)
                .build();
    }



 }
