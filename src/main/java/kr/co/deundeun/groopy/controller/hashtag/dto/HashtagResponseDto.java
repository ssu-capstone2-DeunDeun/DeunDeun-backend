package kr.co.deundeun.groopy.controller.hashtag.dto;

import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class HashtagResponseDto {

    private String name;

    public static List<HashtagResponseDto> ofList(List<UserHashtag> userHashtags){
        return userHashtags.stream()
                .map(tag -> new HashtagResponseDto(tag.getHashtagInfo().getName()))
                .collect(Collectors.toList());

    }

}
