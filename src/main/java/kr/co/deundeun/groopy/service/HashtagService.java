package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dao.HashtagInfoRepository;
import kr.co.deundeun.groopy.domain.hashtag.Hashtag;
import kr.co.deundeun.groopy.domain.hashtag.HashtagInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HashtagService {
    private final HashtagInfoRepository hashtagInfoRepository;

    public List<String> getHashtagNames(){
        return hashtagInfoRepository.findAll().stream()
                .map(HashtagInfo::getName)
                .collect(Collectors.toList());
    }

    public List<HashtagInfo> getHashtagInfos(List<String> hashtagNames){
        return hashtagNames.stream()
                .map(name -> hashtagInfoRepository.findByName(name).orElseThrow(RuntimeException::new))
                .collect(Collectors.toList());
    }

}
