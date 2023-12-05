package kr.co.kshproject.webDemo.Domain.Comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    @Schema(example  = "null")
    private Long id;
    @Schema(example  = "닉네임")
    private String userName;
    @Schema(example  = "댓글")
    private String contents;
    @Schema(example  = "생성일자")
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

