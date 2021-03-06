package kr.co.deundeun.groopy.dto.club;

import kr.co.deundeun.groopy.dao.HashtagInfoRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.constant.CategoryType;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class ClubRequestDto {

    private CategoryType categoryType;

    @Positive
    private int generation;

    private String name;

    private String introduction;

    private String representImageUrl;

    private String backgroundImageUrl;

    private List<String> clubImages;

    private List<Long> hashtagInfoIds;
}
