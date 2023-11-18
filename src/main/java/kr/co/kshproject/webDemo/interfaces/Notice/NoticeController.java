package kr.co.kshproject.webDemo.interfaces.Notice;

import kr.co.kshproject.webDemo.Applicaiton.Notice.NoticeService;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {
   private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService){
        this.noticeService=noticeService;
   }

   @PostMapping
   public ResponseEntity<Notice> save(@RequestBody Notice notice){
        return ResponseEntity.ok(noticeService.save(notice));
   }

    @GetMapping
    public ResponseEntity< Map<String,List> > findAll(){
        return ResponseEntity.ok( noticeService.findAllWithComments(1));
    }

   @GetMapping("/{page}")
    public ResponseEntity< Map<String,List> > findAll(@PathVariable int page){
        return ResponseEntity.ok( noticeService.findAllWithComments(page));
   }

   @GetMapping("/{page}/{id}")
   public ResponseEntity<Notice> findWithCommentsById(@PathVariable int page,@PathVariable Long id){
        Optional<Notice> notice=noticeService.findWithCommentsById(page,id);
        return ResponseEntity.ok( notice.get() );
   }

   @PutMapping("/{id}")
   public ResponseEntity<Notice> update(@PathVariable Long id, @RequestBody Notice notice){
       return ResponseEntity.ok(noticeService.update(id,notice));
   }

   @DeleteMapping("/{id}")
   public void deleteById(@PathVariable Long id){
        noticeService.deleteById(id);
    }

    @DeleteMapping
   public void deleteAll(){
        noticeService.deleteAll();
   }
}
