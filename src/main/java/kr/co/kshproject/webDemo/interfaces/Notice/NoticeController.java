package kr.co.kshproject.webDemo.interfaces.Notice;

import kr.co.kshproject.webDemo.Applicaiton.Notice.NoticeService;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
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
   public ResponseEntity<Notice> save(@Validated @RequestBody NoticeDTO noticeDTO){
        return ResponseEntity.ok(noticeService.save(noticeDTO));
   }

    @GetMapping
    public ResponseEntity< List<NoticeDTO>> findAll(){
        return ResponseEntity.ok( noticeService.findAll());
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity< Map<String,List> > findAll( @Min(1) @PathVariable int page, @Range(min=10,max=100) @PathVariable int size){
        return ResponseEntity.ok( noticeService.findAll(page,size));
   }

   @GetMapping("/{id}")
   public ResponseEntity<Optional<Notice>> findById( @Min(1) @PathVariable Long id){
        Optional<Notice> notice=noticeService.findById(id);
        return ResponseEntity.ok( notice );
   }

   @PutMapping("/{id}")
   public ResponseEntity<Notice> update(@Min(1) @PathVariable Long id,@Validated @RequestBody NoticeDTO noticeDTO){
       return ResponseEntity.ok(noticeService.update(id,noticeDTO));
   }

   @DeleteMapping("/{id}")
   public void deleteById(@Min(1) @PathVariable Long id){
        noticeService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        noticeService.deleteAll();
   }
}
