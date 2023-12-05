package kr.co.kshproject.webDemo.Applicaiton.Comment;

import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import kr.co.kshproject.webDemo.Domain.Comment.CommentDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommentService {
    Comment save(CommentDTO commentDTO);
    List<CommentDTO>  findAll();
    Map<String,List> findAll(int page, int size);
    Optional<Comment> findById(Long id);
    Comment update(Long id, CommentDTO commentDTO);
    void deleteById(Long id);
    void deleteAll();
}
