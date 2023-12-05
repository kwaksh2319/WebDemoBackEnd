package kr.co.kshproject.webDemo.Domain.Notice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Notice")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ")
    @SequenceGenerator(name = "NOTICE_SEQ", sequenceName = "NOTICE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @NotEmpty
    @Setter
    private String username;

    @NotEmpty
    @Setter
    private String title;

    @NotEmpty
    @Setter
    private String contents;

    @NotEmpty
    @Setter
    private String email;

    @NotEmpty
    @Setter
    @Column(name = "created_date")
    private String createdDate;

    @JsonBackReference(value="users-notices")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = true)
    @Setter
    private Users users;

    @JsonManagedReference(value="notices-comments")
    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    public Notice(NoticeDTO noticeDTO){
        this.username=noticeDTO.getUsername();
        this.title=noticeDTO.getTitle();
        this.contents=noticeDTO.getContents();
        this.email=noticeDTO.getEmail();
        this.createdDate=noticeDTO.getCreatedDate();
        //this.users=productsDTO.getCreatedDate();
    }
}
