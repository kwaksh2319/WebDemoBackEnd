package kr.co.kshproject.webDemo.Domain.File;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "File")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_SEQ")
    @SequenceGenerator(name = "FILE_SEQ", sequenceName = "FILE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key
}
