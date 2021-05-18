package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.club.dto.ClubRequestDto;
import kr.co.deundeun.groopy.controller.club.dto.ClubResponseDto;
import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitResponseDto;
import kr.co.deundeun.groopy.controller.post.dto.PostResponseDto;
import kr.co.deundeun.groopy.dao.*;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubAdmin;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.image.ClubImage;
import kr.co.deundeun.groopy.domain.image.Image;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.ClubNotFoundException;
import kr.co.deundeun.groopy.exception.DuplicateResourceException;
import kr.co.deundeun.groopy.exception.NameDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    private final ClubRecruitRepository clubRecruitRepository;

    private final ParticipateRepository participateRepository;

    private final ClubAdminRepository clubAdminRepository;

    private final PostRepository postRepository;

    private final HashtagService hashtagService;

    private final ClubImageRepository clubImageRepository;


    @Transactional
    public void registerClub(User user, ClubRequestDto clubRequestDto) {
        if(isDuplicatedName(clubRequestDto.getName())) throw new NameDuplicateException("동아리 이름이 존재합니다.");

        Club club = clubRequestDto.toClub();

        List<ClubImage> clubImages = clubRequestDto.getClubImages().stream()
                .map(clubImage -> new ClubImage(club, clubImage))
                .collect(Collectors.toList());
        club.updateClubImages(clubImages);
        clubImageRepository.saveAll(clubImages);
        clubRepository.save(club);

        hashtagService.registerClubHashtags(club, clubRequestDto.getClubHashtags());

        ClubRecruit clubRecruit = ClubRecruit.builder()
                .club(club)
                .generation(0)
                .build();
        clubRecruitRepository.save(clubRecruit);

        Participate participate = Participate.builder()
                .user(user)
                .clubRecruit(clubRecruit)
                .build();
        participateRepository.save(participate);

        ClubAdmin clubAdmin = ClubAdmin.builder()
                .userId(user.getId())
                .club(club)
                .build();
        clubAdminRepository.save(clubAdmin);
    }

    @Transactional(readOnly = true)
    public ClubResponseDto getClubInfo(User user, String name) {
        Club club = clubRepository.findByClubName(name).orElseThrow(ClubNotFoundException::new);
        boolean isAdmin = clubAdminRepository.existsByUserIdAndClub(user.getId(), club);
        return getClubInfo(isAdmin, name);
    }

    @Transactional(readOnly = true)
    public ClubResponseDto getClubInfo(boolean isAdmin, String name){
        Club club = clubRepository.findByClubName(name).orElseThrow(ClubNotFoundException::new);
        List<Post> posts = postRepository.findTop3ByClubOrderByViewCount(club);
        List<String> clubImages = clubImageRepository.findAllByClub(club).stream().map(Image::toImageUrl).collect(Collectors.toList());
        Optional<ClubRecruit> clubRecruit = clubRecruitRepository.findTopByClubAndGenerationGreaterThanOrderByCreatedAt(club, 0);

        RecruitResponseDto recruitResponseDto = null;
        if(clubRecruit.isPresent()) recruitResponseDto = RecruitResponseDto.of(clubRecruit.get());

        return ClubResponseDto.of(club, PostResponseDto.listOf(posts), recruitResponseDto, clubImages, isAdmin);
    }

    @Transactional
    public void updateClub(String name, ClubRequestDto clubRequestDto) {
        Club club = clubRepository.findByClubName(name).orElseThrow(ClubNotFoundException::new);
        if (isDuplicatedName(name)) throw new DuplicateResourceException("닉네임 중복");
        club.update(clubRequestDto);
        clubRepository.save(club);
    }

    private boolean isDuplicatedName(String clubName) {
        return clubRepository.existsByClubName(clubName);
    }
}
