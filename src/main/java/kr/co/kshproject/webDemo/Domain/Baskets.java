package kr.co.kshproject.webDemo.Domain;

import kr.co.kshproject.webDemo.Key.BasketId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Baskets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Baskets {

    @EmbeddedId
    private BasketId basketId;

    //status 장바구니 B, 삭제 D, 결재 C
    @NotEmpty
    @Column(name = "STATUS")
    private String status;

    //produtnumber + userid
    @NotNull
    @Column(name = "BIND_NUMBER")
    private String bindNumber;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "ID")
    private Product product;

}

//복합키

