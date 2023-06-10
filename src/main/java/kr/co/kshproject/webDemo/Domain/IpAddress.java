package kr.co.kshproject.webDemo.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Table(name = "IpAddress")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IP_SEQ")
    @SequenceGenerator(name = "IP_SEQ", sequenceName = "IP_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @NotEmpty
    @Setter
    private String Ip;
}
