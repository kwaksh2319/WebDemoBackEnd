package kr.co.kshproject.webDemo.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "PRODUCT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    @SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @NotEmpty
    @Setter
    @Column(name = "PRODUCT_NAME")
    private String productName;

    @NotNull
    @Setter
    private BigDecimal price;

    @NotEmpty
    @Setter
    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @NotEmpty
    @Setter
    @Column(name = "VIDEO_URL")
    private String videoUrl;

    @NotNull
    @Setter
    @Column(name = "DESCRIPTION")
    private String description;
}
