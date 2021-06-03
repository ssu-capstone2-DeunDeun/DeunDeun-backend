package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.dao.PostRepository;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.dto.comment.CommentRequestDto;
import kr.co.deundeun.groopy.dto.comment.CommentResponseDto;
import kr.co.deundeun.groopy.dao.ClubApplyRepository;
import kr.co.deundeun.groopy.dao.CommentRepository;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.AuthorizationException;
import kr.co.deundeun.groopy.exception.LoginException;
import kr.co.deundeun.groopy.helper.ClubApplyHelper;
import kr.co.deundeun.groopy.helper.ClubRecruitHelper;
import kr.co.deundeun.groopy.helper.CommentHelper;
import kr.co.deundeun.groopy.helper.PostHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ClubApplyRepository clubApplyRepository;
    private final PostRepository postRepository;
    private final ClubRecruitRepository clubRecruitRepository;

    @Transactional
    public void commentClubApply(User user, Long applyId, CommentRequestDto commentRequestDto) {

        if (user.getId() == null) throw new LoginException();

        ClubApply clubApply = ClubApplyHelper.findById(clubApplyRepository, applyId);
        Comment parentComment = CommentHelper.findParentCommentById(commentRepository, commentRequestDto.getParentCommentId());
        Comment comment = commentRequestDto.toClubApplyComment(user, clubApply, parentComment);
        commentRepository.save(comment);
    }

    public List<CommentResponseDto> getClubApplyComments(Long applyId) {
        ClubApply clubApply = ClubApplyHelper.findById(clubApplyRepository, applyId);
        List<Comment> comments = clubApply.getComments();
        return CommentResponseDto.ofList(comments);
    }

    @Transactional
    public void updateComment(User user, Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = CommentHelper.findById(commentRepository, commentId);
        if (!user.equals(comment.getUser())) throw new AuthorizationException();
        comment.updateComment(commentRequestDto);
    }

    @Transactional
    public void deleteComment(User user, Long commentId) {
        Comment comment = CommentHelper.findById(commentRepository, commentId);
        if (!user.equals(comment.getUser())) throw new AuthorizationException();
        comment.deleteRelationship();
        commentRepository.delete(comment);
    }


    @Transactional
    public void commentPost(User user, Long postId, CommentRequestDto commentRequestDto) {
        if (user.getId() == null) throw new LoginException(); // 근데 user가 없을 때, getId해도 되나?
        Post post = PostHelper.findById(postRepository, postId);
        Comment parentComment = CommentHelper.findParentCommentById(commentRepository, commentRequestDto.getParentCommentId());
        Comment comment = commentRequestDto.toPostComment(user, post, parentComment);
        commentRepository.save(comment);
    }

    public List<CommentResponseDto> getPostComments(Long postId) {
        Post post = PostHelper.findById(postRepository, postId);
        List<Comment> comments = post.getComments();
        return CommentResponseDto.ofList(comments);
    }

    @Transactional
    public void commentClubRecruit(User user, Long clubRecruitId, CommentRequestDto commentRequestDto){
        if (user.getId() == null) throw new LoginException(); // 근데 user가 없을 때, getId해도 되나?
        ClubRecruit clubRecruit = ClubRecruitHelper.findById(clubRecruitRepository, clubRecruitId);
        Comment parentComment = CommentHelper.findParentCommentById(commentRepository, commentRequestDto.getParentCommentId());
        Comment comment = commentRequestDto.toClubRecruitComment(user, clubRecruit, parentComment);
        commentRepository.save(comment);
    }


    public List<CommentResponseDto> getClubRecruitComments(Long clubRecruitId){
        ClubRecruit clubRecruit = ClubRecruitHelper.findById(clubRecruitRepository, clubRecruitId);
        List<Comment> comments = clubRecruit.getComments();
        return CommentResponseDto.ofList(comments);
    }
}
