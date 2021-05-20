package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.common.page.PageRequestDto;
import kr.co.deundeun.groopy.controller.post.dto.PostRequestDto;
import kr.co.deundeun.groopy.controller.post.dto.PostResponseDto;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.PostImageRepository;
import kr.co.deundeun.groopy.dao.PostRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.image.PostImage;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.exception.BadRequestException;
import kr.co.deundeun.groopy.exception.ClubNotFoundException;
import kr.co.deundeun.groopy.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final ClubRepository clubRepository;

    private final PostImageRepository postImageRepository;

    @Transactional
    public void post(String author, String clubName, PostRequestDto postRequestDto) {
        if (author == null || author.isEmpty()) throw new BadRequestException("동아리에 등록된 인원이 아닙니다.");
        Club club = clubRepository.findByClubName(clubName).orElseThrow(ClubNotFoundException::new);
        createPost(author, club, postRequestDto);
    }

    @Transactional
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IdNotFoundException("게시물을 찾을 수 없습니다."));
        post.increaseViewCount();
        postRepository.save(post);
        return PostResponseDto.of(post);
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDto> getClubPosts(String clubName, PageRequestDto pageRequestDto) {
        Club club = clubRepository.findByClubName(clubName).orElseThrow(ClubNotFoundException::new);
        return postRepository.findAllByClub(club, pageRequestDto.of()).map(PostResponseDto::of);
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDto> getPosts(PageRequestDto pageRequestDto) {
        return postRepository.findAll(pageRequestDto.of()).map(PostResponseDto::of);
    }

    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IdNotFoundException("게시물을 찾을 수 없습니다."));
        List<PostImage> postImages = post.getPostImages();
        postImages = post.updatePostImages(postRequestDto.getPostImageUrls());
        post.update(postRequestDto);

        postImageRepository.saveAll(postImages);
        postRepository.save(post);
    }

    private void createPost(String author, Club club, PostRequestDto postRequestDto) {
        Post post = postRequestDto.toPost(author, club);
        List<PostImage> postImages = PostImage.ofList(postRequestDto.getPostImageUrls(), post);

        postImageRepository.saveAll(postImages);
        postRepository.save(post);
    }
}
