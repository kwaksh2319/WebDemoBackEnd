package kr.co.kshproject.webDemo.Domain.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
    @SequenceGenerator(name = "COMMENT_SEQ", sequenceName = "COMMENT_SEQ", allocationSize = 1)
    private Long id;

    @NotEmpty
    @Setter
    private String userName;

    @NotEmpty
    @Setter
    private String contents;

    @NotEmpty
    @Setter
    @Column(name = "created_date")
    private String createdDate;

    @JsonBackReference(value="notices-comments")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="notice_id")
    @Setter
    private Notice notice;

    @JsonBackReference(value="users-comments")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @Setter
    private Users users;

    public Comment(CommentDTO commentDTO){
        this.userName=commentDTO.getUserName();
        this.contents=commentDTO.getContents();
        this.createdDate=commentDTO.getCreatedDate();
    }
}
