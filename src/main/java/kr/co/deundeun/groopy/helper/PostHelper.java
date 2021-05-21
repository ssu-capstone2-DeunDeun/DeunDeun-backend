package kr.co.deundeun.groopy.helper;

import kr.co.deundeun.groopy.dao.PostRepository;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.exception.IdNotFoundException;

public class PostHelper {

    public static Post findById(PostRepository postRepository, Long postId){
        return postRepository.findById(postId).orElseThrow(() -> new IdNotFoundException("게시물을 찾을 수 없습니다."));
    }
}
