package kr.co.deundeun.groopy.dto.hashtag;

import kr.co.deundeun.groopy.domain.hashtag.HashtagInfo;
import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class HashtagResponseDto {

    private Long id;

    private String name;

    public HashtagResponseDto(HashtagInfo hashtagInfo){
        this.id = hashtagInfo.getId();
        this.name = hashtagInfo.getName();
    }

    public static List<HashtagResponseDto> ofList(List<HashtagInfo> hashtagInfos){
        return hashtagInfos.stream()
                .map(HashtagResponseDto::new)
                .collect(Collectors.toList());
    }

}
