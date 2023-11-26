package kr.co.kshproject.webDemo.Domain.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
    @SequenceGenerator(name = "COMMENT_SEQ", sequenceName = "COMMENT_SEQ", allocationSize = 1)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="notice_id")
    @Setter
    private Notice notice;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @Setter
    private Users users;

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
}
