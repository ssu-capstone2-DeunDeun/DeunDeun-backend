package kr.co.deundeun.groopy.dao;

import kr.co.deundeun.groopy.domain.image.PostImage;
import kr.co.deundeun.groopy.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    List<PostImage> findAllByPost(Post post);
}
