package kr.co.kshproject.webDemo.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Table(name = "File")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_SEQ")
    @SequenceGenerator(name = "FILE_SEQ", sequenceName = "FILE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @Setter
    @NotEmpty
    private String filename;

    @Column(name = "file_path")
    @Setter
    @NotEmpty
    private String filePath;

    //n:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

}
