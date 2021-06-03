package kr.co.deundeun.groopy.helper;

import kr.co.deundeun.groopy.dao.CommentRepository;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.AuthorizationException;
import kr.co.deundeun.groopy.exception.IdNotFoundException;


public class CommentHelper {

    public static Comment findById(CommentRepository commentRepository, Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("존재하지 않는 댓글 ID 입니다."));
    }

    public static Comment findParentCommentById(CommentRepository commentRepository, Long parentCommentId){
        if (parentCommentId == -1L) return null;
        return commentRepository.findById(parentCommentId).orElse(null);
    }
}
