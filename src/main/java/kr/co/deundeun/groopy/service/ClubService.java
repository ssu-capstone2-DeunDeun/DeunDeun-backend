package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.config.AppProperties;
import kr.co.deundeun.groopy.domain.hashtag.HashtagInfo;
import kr.co.deundeun.groopy.dto.club.ClubRequestDto;
import kr.co.deundeun.groopy.dto.club.ClubResponseDto;
import kr.co.deundeun.groopy.dto.common.mail.MailRequestDto;
import kr.co.deundeun.groopy.dao.*;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.ClubNotFoundException;
import kr.co.deundeun.groopy.exception.DuplicateResourceException;
import kr.co.deundeun.groopy.exception.LoginException;
import kr.co.deundeun.groopy.exception.NameDuplicateException;
import kr.co.deundeun.groopy.helper.ClubHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    private final ClubRecruitRepository clubRecruitRepository;

    private final ParticipateRepository participateRepository;

    private final PostRepository postRepository;

    private final HashtagService hashtagService;

    private final JavaMailSender javaMailSender;

    private final AppProperties appProperties;

    public void registerClub(User user, ClubRequestDto clubRequestDto) {
        if (user.getId() == null) throw new LoginException();
        if (isDuplicatedName(clubRequestDto.getName()))
            throw new NameDuplicateException("동아리 이름이 존재합니다.");

        List<HashtagInfo> hashtagInfos =
                hashtagService.getHashtagInfos(clubRequestDto.getHashtagInfoIds());

        Club club = new Club(clubRequestDto, hashtagInfos);
        clubRepository.save(club);

        Participate participate = new Participate(user, club, true);
        participateRepository.save(participate);

        MailRequestDto.sendRegisterClub(javaMailSender, appProperties, user, club);
    }

    @Transactional(readOnly = true)
    public ClubResponseDto getClubInfo(User user, String name) {
        Club club = clubRepository.findByClubName(name).orElseThrow(ClubNotFoundException::new);
        boolean isAdmin = participateRepository.findByUserAndClub(user, club).isAdmin();
        return getClubInfo(isAdmin, name);
    }

    @Transactional(readOnly = true)
    public ClubResponseDto getClubInfo(boolean isAdmin, String name) {
        Club club = clubRepository.findByClubName(name).orElseThrow(ClubNotFoundException::new);
        List<Post> posts = postRepository.findTop3ByClubOrderByViewCount(club);
        ClubRecruit clubRecruit = clubRecruitRepository.findTopByClubOrderByCreatedAt(club);
        return ClubResponseDto.of(club, posts, clubRecruit, isAdmin);
    }

    public void updateClub(Long clubId, ClubRequestDto clubRequestDto) {
        Club club = ClubHelper.findClubById(clubRepository, clubId);
        if (isDuplicatedName(clubRequestDto.getName()))
            throw new DuplicateResourceException("닉네임 중복");
        clubRepository.save(club.update(clubRequestDto));
    }

    private boolean isDuplicatedName(String clubName) {
        return clubRepository.existsByClubName(clubName);
    }

    public void approveClub(Long clubId) {
        Club club = ClubHelper.findClubById(clubRepository, clubId);
        club.setApproved(true);
        clubRepository.save(club);
    }

    public void deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
    }
}
