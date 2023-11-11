package kr.co.kshproject.webDemo.Domain.Notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private String email;
    private String createdDate;

    // Constructor that takes an entity Notice
    public NoticeDTO(Notice notice) {
        this.id = notice.getId();
        this.username = notice.getUsername();
        this.title = notice.getTitle();
        this.contents = notice.getContents();
        this.email = notice.getEmail();
        this.createdDate = notice.getCreatedDate();
    }
}
