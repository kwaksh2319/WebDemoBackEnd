package kr.co.kshproject.webDemo.Domain.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private String username; //사용자 아이디
    private String email; //이메일
    private String name; //사용자 이름
    private Long level; // 계정권한 레벨
    private String password;

    public UsersDTO(Users users) {
        this.username = users.getUsername();
        this.level=users.getLevel();
        this.password= users.getPassword();
        this.email= users.getEmail();
        this.name=users.getName();
    }
}
