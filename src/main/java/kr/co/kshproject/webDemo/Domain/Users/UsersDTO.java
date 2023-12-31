package kr.co.kshproject.webDemo.Domain.Users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    @Schema(example  = "null")
    private Long id; //사용자 아이디
    @Schema(example  = "사용자 아이디")
    @NotBlank
    @NotNull
    private String username; //사용자 아이디
    @Schema(example  = "test@mail.com")
    @Email
    @NotBlank
    @NotNull
    private String email; //이메일
    @Schema(example  = "사용자 이름")
    @NotBlank
    @NotNull
    private String name; //사용자 이름
    @Schema(example  = "1")
    @NotNull
    @Range(min=1,max=100)
    private Long level; // 계정권한 레벨
    @Schema(example  = "password")
    @NotBlank
    @NotNull
    private String password;

    public UsersDTO(Users users) {
        this.id=users.getId();
        this.username = users.getUsername();
        this.level=users.getLevel();
        this.password= users.getPassword();
        this.email= users.getEmail();
        this.name=users.getName();
    }
}
