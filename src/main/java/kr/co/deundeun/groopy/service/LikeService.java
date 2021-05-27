package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dao.PostRepository;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.post.PostLike;
import kr.co.deundeun.groopy.dto.like.LikeResponseDto;
import kr.co.deundeun.groopy.dao.ClubLikeRepository;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.PostLikeRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubLike;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.LoginException;
import kr.co.deundeun.groopy.helper.ClubHelper;
import kr.co.deundeun.groopy.helper.PostHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final ClubLikeRepository clubLikeRepository;

    private final PostLikeRepository postLikeRepository;

    private final PostRepository postRepository;

    private final ClubRepository clubRepository;

    @Transactional
    public void likeClub(User user, Long clubId) {
        if (user.getId() == null) throw new LoginException();

        Club club = ClubHelper.findClubById(clubRepository, clubId);

        ClubLike clubLike = clubLikeRepository.findByClubAndUser(club, user);

        if (clubLike == null) {
            clubLike = new ClubLike(club, user);
            club.increaseLikeCount();
        } else {
            if (clubLike.isLiked())
                club.decreaseLikeCount();
            else
                club.increaseLikeCount();
            clubLike.updateLike();
        }
        clubLikeRepository.save(clubLike);
    }

    public LikeResponseDto getClubLike(User user, Long clubId) {
        if (user.getId() == null) return new LikeResponseDto();

        Club club = ClubHelper.findClubById(clubRepository, clubId);
        ClubLike clubLike = clubLikeRepository.findByClubAndUser(club, user);
        if (clubLike != null) return new LikeResponseDto(user, clubLike);
        return new LikeResponseDto();
    }

    public void likePost(User user, Long postId) {
        if (user.getId() == null) throw new LoginException();

        Post post = PostHelper.findById(postRepository, postId);

        PostLike postLike = postLikeRepository.findByPostAndUser(post, user);

        if (postLike == null) {
            postLike = new PostLike(post, user);
            post.increaseLikeCount();
        } else {
            if (postLike.isLiked())
                post.decreaseLikeCount();
            else
                post.increaseLikeCount();
            postLike.updateLike();
        }

        postLikeRepository.save(postLike);
    }

    public LikeResponseDto getPostLike(User user, Long postId) {
        if (user.getId() == null) return new LikeResponseDto();

        Post post = PostHelper.findById(postRepository, postId);
        PostLike postLike = postLikeRepository.findByPostAndUser(post, user);
        if (postLike != null) return new LikeResponseDto(user, postLike);
        return new LikeResponseDto();
    }
}
