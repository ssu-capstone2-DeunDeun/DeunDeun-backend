package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.domain.hashtag.HashtagInfo;
import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import kr.co.deundeun.groopy.dto.club.ClubResponseDto;
import kr.co.deundeun.groopy.dto.hashtag.HashtagResponseDto;
import kr.co.deundeun.groopy.dto.like.liked.LikeListResponseDto;
import kr.co.deundeun.groopy.dto.user.UserRequestDto;
import kr.co.deundeun.groopy.dto.user.UserResponseDto;
import kr.co.deundeun.groopy.dao.*;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubLike;
import kr.co.deundeun.groopy.domain.post.PostLike;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.DuplicateResourceException;
import kr.co.deundeun.groopy.exception.NameDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserHashtagRepository userHashtagRepository;
    private final HashtagService hashtagService;
    private final ClubLikeRepository clubLikeRepository;
    private final PostLikeRepository postLikeRepository;
    private final ClubRepository clubRepository;
    private final PostRepository postRepository;
    private final ParticipateRepository participateRepository;

    public boolean isDuplicatedNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public UserResponseDto signup(User user, UserRequestDto userRequestDto) {
        if (user.getNickname() != null) throw new NameDuplicateException("이미 등록된 회원입니다.");
        user.saveSignupInfo(userRequestDto);

        userRepository.save(user);
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(User user) {
        return new UserResponseDto(user);
    }

    public void updateNickname(User user, String nickname) {
        if (isDuplicatedNickname(nickname))
            throw new DuplicateResourceException("중복된 닉네임입니다.");

        user.updateNickname(nickname);
        userRepository.save(user);
    }

    public void updateUserImageUrl(User user, String userImageUrl) {
        user.updateUserImageUrl(userImageUrl);
        userRepository.save(user);
    }

    public void updateHashtags(User user, List<Long> hashtagInfoIds) {
        if (hashtagInfoIds.size() < 3)
            throw new IllegalArgumentException("해시태그는 3개 이상 등록해야 합니다.");

        List<HashtagInfo> hashtagInfos = hashtagService.getHashtagInfos(hashtagInfoIds);
        user.setUserHashtags(hashtagInfos);
    }

    public List<HashtagResponseDto> getHashtags(User user) {
        List<HashtagInfo> hashtagInfos = userHashtagRepository
                .findAllByUser(user)
                .stream()
                .map(UserHashtag::getHashtagInfo)
                .collect(Collectors.toList());

        return HashtagResponseDto.ofList(hashtagInfos);
    }

    public LikeListResponseDto getLikes(User user) {
        List<ClubLike> clubLikes = clubLikeRepository.findAllByUser(user);
        List<Club> clubs = clubRepository.findAllById(clubLikes.stream()
                .map(clubLike -> clubLike.getClub().getId()).collect(Collectors.toList()));

        List<PostLike> postLikes = postLikeRepository.findAllByUser(user);
        List<Post> posts = postRepository.findAllById(postLikes.stream()
                .map(postLike -> postLike.getPost().getId()).collect(Collectors.toList()));

        return LikeListResponseDto.of(clubs, posts);
    }

    public List<ClubResponseDto> getClubs(User user) {
        List<Participate> participates = participateRepository.findAllByUser(user);
        List<Club> clubs = participates.stream()
                .map(Participate::getClub)
                .collect(Collectors.toList());
        return ClubResponseDto.listOf(clubs);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
