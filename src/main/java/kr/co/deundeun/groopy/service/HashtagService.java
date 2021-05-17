package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dao.ClubHashtagRepository;
import kr.co.deundeun.groopy.dao.HashtagInfoRepository;
import kr.co.deundeun.groopy.dao.UserHashtagRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.hashtag.ClubHashtag;
import kr.co.deundeun.groopy.domain.hashtag.HashtagInfo;
import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HashtagService {
    private final HashtagInfoRepository hashtagInfoRepository;
    private final ClubHashtagRepository clubHashtagRepository;
    private final UserHashtagRepository userHashtagRepository;

    public List<String> getHashtagNames() {
        return hashtagInfoRepository.findAll().stream()
                .map(HashtagInfo::getName)
                .collect(Collectors.toList());
    }

    public List<HashtagInfo> getHashtagInfos(List<String> hashtagNames) {
        return hashtagNames.stream()
                .map(name -> hashtagInfoRepository.findByName(name).orElseThrow(RuntimeException::new))
                .collect(Collectors.toList());
    }

    public void registerClubHashtags(Club club, List<String> hashtagNames) {
        clubHashtagRepository.saveAll(getHashtagInfos(hashtagNames).stream()
                .map(tag -> ClubHashtag.builder().club(club).hashtagInfo(tag).build())
                .collect(Collectors.toList()));
    }

    public void registerUserHashtags(User user, List<String> hashtagNames) {
        userHashtagRepository.saveAll(getHashtagInfos(hashtagNames).stream()
                .map(tag -> UserHashtag.builder().user(user).hashtagInfo(tag).build())
                .collect(Collectors.toList()));
    }
}
