package kr.co.kshproject.webDemo.Applicaiton.Comment;

import kr.co.kshproject.webDemo.Domain.Comment.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment save(int page,Long noticeId,Comment comment);
    List<Comment> findAll();
    Optional<Comment> findById(Long id);
    Comment update(Long id,Comment saveComment);
    void deleteById(Long id);
    void deleteAll();
}
