package kr.co.kshproject.webDemo.Domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private Long noticeId; // 대신 Notice 엔티티의 ID를 저장
    private String userName;
    private String contents;
    private String createdDate;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.noticeId = comment.getNotice().getId(); // Notice의 ID를 가져옵니다.
        this.userName = comment.getUserName();
        this.contents = comment.getContents();
        this.createdDate = comment.getCreatedDate();
    }
}

