package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.like.dto.LikeResponseDto;
import kr.co.deundeun.groopy.dao.ClubLikeRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.PostLikeRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.like.ClubLike;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.ClubNotFoundException;
import kr.co.deundeun.groopy.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final ClubLikeRepository clubLikeRepository;

    private final PostLikeRepository postLikeRepository;

    private final ClubRepository clubRepository;

    @Transactional
    public void likeClub(User user, String clubName) {
        if(user.getId() == null) throw new LoginException();

        Club club = clubRepository.findByClubName(clubName).orElseThrow(ClubNotFoundException::new);

        ClubLike clubLike;

        if (clubLikeRepository.existsByClubAndUser(club, user)) {
            clubLike = clubLikeRepository.findByClubAndUser(club, user);
            if(clubLike.isLiked())
                club.decreaseLikeCount();
            else
                club.increaseLikeCount();
            clubLike.updateLike();
        } else {
            clubLike = ClubLike.builder()
                    .club(club)
                    .user(user)
                    .build();
            club.increaseLikeCount();
        }
        clubRepository.save(club);
        clubLikeRepository.save(clubLike);
    }

    public LikeResponseDto getClubLike(User user, String clubName) {
        if(user.getId() == null) return new LikeResponseDto();

        Club club = clubRepository.findByClubName(clubName).orElseThrow(ClubNotFoundException::new);
        if (clubLikeRepository.existsByClubAndUser(club, user))
            return LikeResponseDto.of(clubLikeRepository.findByClubAndUser(club, user));
        return new LikeResponseDto();
    }
}
