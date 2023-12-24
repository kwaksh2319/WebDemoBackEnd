package kr.co.kshproject.webDemo.Domain.Users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.co.kshproject.webDemo.Domain.Baskets.Baskets;
import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Orders.Orders;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "Users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @NotEmpty
    @Column(name = "username")
    @Setter
    private String username; //사용자 아이디

    @Email
    @Setter
    private String email; //이메일

    @NotEmpty
    @Setter
    private String name; //사용자 이름

    @NotNull
    @Setter
    private Long level; // 계정권한 레벨

    @NotBlank
    @Setter
    private String password;

    @JsonManagedReference(value="users-comments")
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @JsonManagedReference(value="users-notices")
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Notice> notices = new HashSet<>();

    @JsonManagedReference(value="users-baskets")
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Baskets> baskets = new HashSet<>();

    @JsonManagedReference(value="users-orders")
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Orders> orders = new HashSet<>();

    public Users(UsersDTO usersDTO){
        this.username=usersDTO.getUsername();
        this.name=usersDTO.getName();
        this.level= usersDTO.getLevel();
        this.password=usersDTO.getPassword();
        this.email=usersDTO.getEmail();
    }
}
