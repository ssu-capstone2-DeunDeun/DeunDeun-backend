package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dao.ClubHashtagRepository;
import kr.co.deundeun.groopy.dao.HashtagInfoRepository;
import kr.co.deundeun.groopy.dao.UserHashtagRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import kr.co.deundeun.groopy.domain.hashtag.HashtagInfo;
import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.dto.hashtag.HashtagResponseDto;
import kr.co.deundeun.groopy.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HashtagService {

  private final HashtagInfoRepository hashtagInfoRepository;

  public List<String> getHashtagNames() {
    return hashtagInfoRepository.findAll()
                                .stream()
                                .map(HashtagInfo::getName)
                                .collect(Collectors.toList());
  }

  public List<HashtagInfo> getHashtagInfos(List<Long> hashtagInfoIds) {
    return hashtagInfoIds.stream()
                         .map(id -> hashtagInfoRepository.findById(id)
                                                         .orElseThrow(() -> new IdNotFoundException(
                                                             "등록된 해시태그 ID가 아닙니다.")))
                         .collect(Collectors.toList());
  }

  public List<HashtagResponseDto> getAllHashtagInfos() {
    return HashtagResponseDto.ofList(hashtagInfoRepository.findAll());
  }


}
