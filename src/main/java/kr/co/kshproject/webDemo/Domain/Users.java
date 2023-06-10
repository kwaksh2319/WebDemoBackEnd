package kr.co.kshproject.webDemo.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name = "users")
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

    @NotEmpty
    @Setter
    private String email; //이메일

    @NotEmpty
    @Setter
    private String name; //사용자 이름

    @NotNull
    @Setter
    private Long level; // 계정권한 레벨

    @Setter
    private String password;

    //권한 관련 함수
}
