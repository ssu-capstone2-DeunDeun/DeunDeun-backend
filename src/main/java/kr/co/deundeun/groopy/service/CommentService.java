package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.dto.comment.CommentRequestDto;
import kr.co.deundeun.groopy.dto.comment.CommentResponseDto;
import kr.co.deundeun.groopy.dao.ClubApplyRepository;
import kr.co.deundeun.groopy.dao.CommentRepository;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.LoginException;
import kr.co.deundeun.groopy.helper.ClubApplyHelper;
import kr.co.deundeun.groopy.helper.CommentHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final ClubApplyRepository clubApplyRepository;

    public void commentApply(User user, Long applyId, CommentRequestDto commentRequestDto) {
        if (user.getId() == null) throw new LoginException();
        ClubApply clubApply = ClubApplyHelper.findById(clubApplyRepository, applyId);

        Comment comment;

        Long parentCommentId = commentRequestDto.getParentCommentId();
        if (parentCommentId != null) {
            Comment parentComment = CommentHelper
                    .findById(commentRepository, parentCommentId);
            comment = commentRequestDto.toComment(user, clubApply, parentComment);
        } else {
            comment = commentRequestDto.toComment(user, clubApply);
        }
        commentRepository.save(comment);
        clubApplyRepository.save(clubApply);
    }

    public List<CommentResponseDto> getApplyComments(Long applyId) {
        ClubApply clubApply = ClubApplyHelper.findById(clubApplyRepository, applyId);
        List<Comment> comments = commentRepository.findByClubApply(clubApply);
        return CommentResponseDto.ofList(comments);
    }

    public void updateComment(User user, Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = CommentHelper.findById(commentRepository, commentId, user);
        comment.update(commentRequestDto);
        commentRepository.save(comment);
    }

    public void deleteComment(User user, Long commentId) {
        Comment comment = CommentHelper.findById(commentRepository, commentId, user);
        commentRepository.delete(comment);
    }


}
