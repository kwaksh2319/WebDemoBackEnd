package kr.co.kshproject.webDemo.Domain.BeginOrder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.co.kshproject.webDemo.Domain.Orders.Orders;
import kr.co.kshproject.webDemo.Domain.Products.Products;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BeginOrder")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BeginOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BEGINORDER_SEQ")
    @SequenceGenerator(name = "BEGINORDER_SEQ", sequenceName = "BEGINORDER_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    //프로덕트  ID
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    @Setter
    private Products products;
    //주문 ID
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    @Setter
    private Orders orders;

    @NotNull
    @Setter
    private Long quantity;

    @NotEmpty
    @Setter
    private String picture;

    @NotEmpty
    @Setter
    @Column(name = "created_date")
    private String createdDate;

    @NotEmpty
    @Setter
    @Column(name = "update_date")
    private String updateDate;
}