package kr.co.kshproject.webDemo.interfaces.Comment;

import kr.co.kshproject.webDemo.Applicaiton.Comment.CommentService;
import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import kr.co.kshproject.webDemo.Domain.Comment.CommentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @PostMapping
    public ResponseEntity<Comment> save(@RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(commentService.save(commentDTO));
    }

    @GetMapping
    public ResponseEntity< List<CommentDTO>> findAll(){
        return ResponseEntity.ok( commentService.findAll());
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Map<String,List>> findAll(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok( commentService.findAll(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comment>> findById(@PathVariable Long id){
        Optional<Comment> comment=commentService.findById(id);
        return ResponseEntity.ok( comment );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(commentService.update(id,commentDTO));
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
