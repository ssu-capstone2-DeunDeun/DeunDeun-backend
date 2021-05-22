package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.club.dto.ClubRequestDto;
import kr.co.deundeun.groopy.controller.club.dto.ClubResponseDto;
import kr.co.deundeun.groopy.controller.common.page.PageRequestDto;
import kr.co.deundeun.groopy.dao.*;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubAdmin;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.image.ClubImage;
import kr.co.deundeun.groopy.domain.like.ClubLike;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.ClubNotFoundException;
import kr.co.deundeun.groopy.exception.DuplicateResourceException;
import kr.co.deundeun.groopy.exception.LoginException;
import kr.co.deundeun.groopy.exception.NameDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    private final ClubRecruitRepository clubRecruitRepository;

    private final ParticipateRepository participateRepository;

    private final ClubAdminRepository clubAdminRepository;

    private final PostRepository postRepository;

    private final HashtagService hashtagService;

    private final ClubImageRepository clubImageRepository;

    private final ClubLikeRepository clubLikeRepository;

    public void registerClub(User user, ClubRequestDto clubRequestDto) {
        if(user.getId() == null) throw new LoginException();
        if(isDuplicatedName(clubRequestDto.getName())) throw new NameDuplicateException("동아리 이름이 존재합니다.");

        Club club = createClub(clubRequestDto);

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
        ClubRecruit clubRecruit = clubRecruitRepository.findTopByClubAndGenerationGreaterThanOrderByCreatedAt(club, 0);
        return ClubResponseDto.of(club, posts, clubRecruit, isAdmin);
    }

    public void updateClub(String name, ClubRequestDto clubRequestDto) {
        Club club = clubRepository.findByClubName(name).orElseThrow(ClubNotFoundException::new);
        if (isDuplicatedName(name)) throw new DuplicateResourceException("닉네임 중복");
        clubRepository.save(club.update(clubRequestDto));
    }

    private Club createClub(ClubRequestDto clubRequestDto){
        List<ClubImage> clubImages = ClubImage.ofList(clubRequestDto.getClubImages());
        Club club = clubRequestDto.toClub();
        club.setClubImages(clubImages);
        hashtagService.registerClubHashtags(club, clubRequestDto.getClubHashtags());
        clubImageRepository.saveAll(clubImages);
        return clubRepository.save(club);
    }

    private boolean isDuplicatedName(String clubName) {
        return clubRepository.existsByClubName(clubName);
    }

    public List<ClubResponseDto> getLikedClubs(User user) {
        if(user.getId() == null) throw new LoginException();
        List<ClubLike> clubLikes = clubLikeRepository.findAllByUser(user);
        List<Club> clubs = clubLikes.stream().map(ClubLike::getClub).collect(Collectors.toList());
        return ClubResponseDto.listOf(clubs);
    }
}
