package kr.co.kshproject.webDemo.Domain.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Comment {

    @Schema(example  = "null")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
    @SequenceGenerator(name = "COMMENT_SEQ", sequenceName = "COMMENT_SEQ", allocationSize = 1)
    private Long id;

    @Schema(example  = "1")
    @JsonBackReference(value="notices-comments")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="notice_id")
    @Setter
    private Notice notice;

    @Schema(example  = "1")
    @JsonBackReference(value="users-comments")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @Setter
    private Users users;

    @Schema(example  = "닉네임")
    @NotEmpty
    @Setter
    private String userName;

    @Schema(example  = "내용")
    @NotEmpty
    @Setter
    private String contents;

    @Schema(example  = "생성날짜")
    @NotEmpty
    @Setter
    @Column(name = "created_date")
    private String createdDate;
}
