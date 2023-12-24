package kr.co.kshproject.webDemo.Domain.Comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    @Schema(example  = "null")
    private Long id;
    @Schema(example  = "닉네임")
    @NotNull
    @NotBlank
    private String userName;
    @Schema(example  = "댓글")
    @NotNull
    @NotBlank
    private String contents;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @NotBlank
    private String createdDate;
    @Schema(example  = "null")
    private Long noticeId;
    @Schema(example  = "null")
    private Long userId;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.userName = comment.getUserName();
        this.contents = comment.getContents();
        this.createdDate = comment.getCreatedDate();
        if(comment.getNotice()!=null) {
            this.noticeId = comment.getNotice().getId();
        }
        if(comment.getUsers()!=null) {
            this.userId = comment.getUsers().getId();
        }
    }
}

