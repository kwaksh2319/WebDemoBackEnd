package kr.co.kshproject.webDemo.Domain.Notice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
    @Schema(example  = "null")
    private Long id;
    @Schema(example  = "닉네임")
    private String username;
    @Schema(example  = "제목")
    private String title;
    @Schema(example  = "내용")
    private String contents;
    @Schema(example  = "이메일")
    private String email;
    @Schema(example  = "생성날짜")
    private String createdDate;
    @Schema(example = "null")
    private long userId;

    public NoticeDTO(Notice notice) {
        this.id = notice.getId();
        this.username = notice.getUsername();
        this.title = notice.getTitle();
        this.contents = notice.getContents();
        this.email = notice.getEmail();
        this.createdDate = notice.getCreatedDate();
        if(notice.getUsers()!=null){
            this.userId=notice.getUsers().getId();
        }
    }
}
