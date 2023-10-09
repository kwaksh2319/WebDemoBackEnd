package kr.co.kshproject.webDemo.interfaces.Notice;

import kr.co.kshproject.webDemo.Applicaiton.Notice.NoticeService;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<List<Notice>> findAll(){
        return ResponseEntity.ok( noticeService.findAll());
   }

   @GetMapping("/{id}")
   public Optional<Notice> findById(@PathVariable Long id){
        return noticeService.findById(id);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Notice> update(@PathVariable Long id, @RequestBody Notice notice){
       return ResponseEntity.ok(noticeService.update(id,notice));
   }

   @DeleteMapping
   public void deleteAll(){
        noticeService.deleteAll();
   }
}
