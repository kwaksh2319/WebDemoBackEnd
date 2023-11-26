package kr.co.kshproject.webDemo.interfaces.Comment;

import kr.co.kshproject.webDemo.Applicaiton.Comment.CommentService;
import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @PostMapping("/{page}/{noticeid}/{userid}")
    public ResponseEntity<Comment> save(@PathVariable int page, @PathVariable Long noticeid,@PathVariable Long userid, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.save(page,noticeid,userid,comment));
    }

    @GetMapping
    public ResponseEntity<List<Comment>> findAll(){
        return ResponseEntity.ok( commentService.findAll());
    }

    @GetMapping("/{id}")
    public Optional<Comment> findById(@PathVariable Long id){
        return commentService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody Comment comment){
        return ResponseEntity.ok(commentService.update(id,comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public void deleteAll(){
        commentService.deleteAll();
    }
}
