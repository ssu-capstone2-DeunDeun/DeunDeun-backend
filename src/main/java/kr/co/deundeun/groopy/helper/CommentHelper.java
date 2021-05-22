package kr.co.deundeun.groopy.helper;

import kr.co.deundeun.groopy.dao.CommentRepository;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.AuthorizationException;
import kr.co.deundeun.groopy.exception.IdNotFoundException;


public class CommentHelper {

    public static Comment findById(CommentRepository commentRepository, Long id, User user) {
        Comment comment = findById(commentRepository, id);
        if (!user.equals(comment.getUser())) throw new AuthorizationException();
        return comment;
    }

    public static Comment findById(CommentRepository commentRepository, Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("존재하지 않는 댓글 ID 입니다."));
    }
}
