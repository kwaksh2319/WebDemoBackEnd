package kr.co.kshproject.webDemo.Domain.Comment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommentCustomRepository {
    Optional<Comment> findById(Long id);
    List<CommentDTO> findAll();
    Map<String,List> findAll(int page, int size);
}
