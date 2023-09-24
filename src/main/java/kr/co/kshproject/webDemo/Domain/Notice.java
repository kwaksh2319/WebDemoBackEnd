/*
package kr.co.kshproject.webDemo.Domain;*/
/*
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Notice")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ")
    @SequenceGenerator(name = "NOTICE_SEQ", sequenceName = "NOTICE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @NotEmpty
    @Setter
    private String title;

    @NotEmpty
    @Setter
    private String contents;

    @NotEmpty
    @Setter
    private String username;

    @NotEmpty
    @Setter
    private String email;

    @NotEmpty
    @Setter
    @Column(name = "created_date")
    private String createdDate;

    //1: n 관계
    @JsonIgnore
    @OneToMany(mappedBy = "notice",fetch = FetchType.EAGER)
    private List<File> file= new ArrayList<>();

}*/
