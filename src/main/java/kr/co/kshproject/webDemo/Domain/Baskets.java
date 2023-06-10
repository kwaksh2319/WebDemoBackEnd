package kr.co.kshproject.webDemo.Domain;

import kr.co.kshproject.webDemo.Key.BasketId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "Baskets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Baskets {

    @EmbeddedId
    private BasketId basketId;

    @NotEmpty
    @Column(name = "PRODUCT_NAME")
    private String productName;

    @NotNull
    private BigDecimal price;

    //status 장바구니 B, 삭제 D, 결재 C
    @NotEmpty
    @Column(name = "STATUS")
    private String status;

    @NotNull
    private Long count;

}

//복합키

