package kr.co.kshproject.webDemo.Domain.Notice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
    @Schema(example  = "null")
    private Long id;
    @Schema(example  = "닉네임")
    @NotNull
    @NotBlank
    private String username;
    @Schema(example  = "제목")
    @NotNull
    @NotBlank
    private String title;
    @Schema(example  = "내용")
    @NotNull
    @NotBlank
    private String contents;
    @Schema(example  = "test@test.com")
    @Email
    @NotBlank
    @NotNull
    private String email;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank
    @NotNull
    private String createdDate;
    @Schema(example = "null")
    private Long userId;

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
