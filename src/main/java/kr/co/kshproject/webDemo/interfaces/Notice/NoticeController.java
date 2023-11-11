package kr.co.kshproject.webDemo.interfaces.Notice;

import kr.co.kshproject.webDemo.Applicaiton.Notice.NoticeService;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeDTO;
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
    public ResponseEntity<List<NoticeDTO>> findAll(){
        return ResponseEntity.ok( noticeService.findAllWithComments());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Notice> findWithCommentsById(@PathVariable Long id){
        log.info("controller startS");
        try{
            Optional<Notice> notice=noticeService.findWithCommentsById(id);
            if(notice.isPresent()){
                log.info("present");
            }else{
                log.info("not present");
            }
               //log.info(notice.get().getComments().toString());
              log.info(notice.get().getTitle());
            return ResponseEntity.ok( notice.get() );

        }catch (Exception e){
            log.info("errrrorrrrr "+e.toString());

        }


        return ResponseEntity.ok( null );
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
