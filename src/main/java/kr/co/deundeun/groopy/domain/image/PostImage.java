package kr.co.deundeun.groopy.domain.image;

import javax.persistence.FetchType;

import kr.co.deundeun.groopy.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Entity
public class PostImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public PostImage(String imageUrl, Post post){
        this.imageUrl = imageUrl;
        this.post = post;
    }

    public void setPost(Post post){
        if(this.post != null)
            this.post.getPostImages().remove(this);

        this.post = post;
        post.getPostImages().add(this);
    }

    public static PostImage of(String postImageUrl, Post post){
        return new PostImage(postImageUrl, post);
    }

    public static List<PostImage> ofList(List<String> postImageUrls, Post post) {
        return postImageUrls.stream()
                .map(postImageUrl -> PostImage.of(postImageUrl, post))
                .collect(Collectors.toList());
    }

}
