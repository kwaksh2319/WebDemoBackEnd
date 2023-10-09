package kr.co.kshproject.webDemo.Domain.Comment;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
    @SequenceGenerator(name = "COMMENT_SEQ", sequenceName = "COMMENT_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="notice_id")
    private Notice notice;

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
