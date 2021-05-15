package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.controller.post.dto.PostRequestDto;
import kr.co.deundeun.groopy.controller.post.dto.PostResponseDto;
import kr.co.deundeun.groopy.dao.ClubRepository;
import kr.co.deundeun.groopy.dao.PostRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.BadRequestException;
import kr.co.deundeun.groopy.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.NameNotFoundException;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final ClubRepository clubRepository;

    public void post(String author, String clubName, PostRequestDto postRequestDto){
        Club club = clubRepository.findByClubName(clubName).orElseThrow(RuntimeException::new);
        if(author == null || author.isEmpty()) throw new BadRequestException("동아리에 등록된 인원이 아닙니다.");

        Post post = postRequestDto.toPost(author, club);
        postRepository.save(post);
    }

    public PostResponseDto getPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(RuntimeException::new);

        return PostResponseDto.of(post);
    }


}
