package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dto.common.page.PageRequestDto;
import kr.co.deundeun.groopy.dto.post.PostRequestDto;
import kr.co.deundeun.groopy.dto.post.PostResponseDto;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.PostRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.helper.ClubHelper;
import kr.co.deundeun.groopy.helper.PostHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;

    private final ClubRepository clubRepository;

    public PostResponseDto post(String author, Long clubId, PostRequestDto postRequestDto) {
        Club club = ClubHelper.findClubById(clubRepository, clubId);
        return PostResponseDto.of(createPost(author, club, postRequestDto));
    }

    public PostResponseDto getPost(Long postId) {
        Post post = PostHelper.findById(postRepository, postId);
        post.increaseViewCount();
        postRepository.save(post);
        return PostResponseDto.of(post);
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDto> getClubPosts(Long clubId, PageRequestDto pageRequestDto) {
        Club club = ClubHelper.findClubById(clubRepository, clubId);
        return postRepository.findAllByClub(club, pageRequestDto.of()).map(PostResponseDto::of);
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDto> getPosts(PageRequestDto pageRequestDto) {
        return postRepository.findAll(pageRequestDto.of()).map(PostResponseDto::of);
    }

    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = PostHelper.findById(postRepository, postId);
        post.update(postRequestDto);
        return PostResponseDto.of(post);
    }

    private Post createPost(String author, Club club, PostRequestDto postRequestDto) {
        Post post = postRequestDto.toPost(author, club);
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
