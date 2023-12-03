package kr.co.kshproject.webDemo.Domain.Notice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Notice")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Notice {
    @Schema(example  = "null")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ")
    @SequenceGenerator(name = "NOTICE_SEQ", sequenceName = "NOTICE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @Schema(example  = "닉네임")
    @NotEmpty
    @Setter
    private String username;

    @Schema(example  = "제목")
    @NotEmpty
    @Setter
    private String title;

    @Schema(example  = "내용")
    @NotEmpty
    @Setter
    private String contents;

    @Schema(example  = "이메일")
    @NotEmpty
    @Setter
    private String email;

    @Schema(example  = "생성날짜")
    @NotEmpty
    @Setter
    @Column(name = "created_date")
    private String createdDate;

    @Schema(example = "{'id': 1, 'username': '사용자 아이디', 'email': '이메일', 'name': '사용자 이름', 'level': '2', 'password': 'passwords', 'comments': 'null', 'notices': 'null', 'baskets': 'null', 'orders': 'null'}")
    @JsonBackReference(value="users-notices")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @Setter
    private Users users;

    @Schema(example  = "null")
    @JsonManagedReference(value="notices-comments")
    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
}
