package kr.co.kshproject.webDemo.Domain.Baskets;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.co.kshproject.webDemo.Domain.Products.Products;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Baskets")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Baskets {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BASKET_SEQ")
    @SequenceGenerator(name = "BASKET_SEQ", sequenceName = "BASKET_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @NotEmpty
    @Setter
    private String productName;

    @NotEmpty
    @Setter
    private String productStatus;

    @NotNull
    @Setter
    private Long quantity;

    @NotEmpty
    @Setter
    @Column(name = "created_date")
    private String createDate;

    @NotEmpty
    @Setter
    @Column(name = "update_date")
    private String updateDate;

    @JsonBackReference(value="products-baskets")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    @Setter
    private Products products;

    @JsonBackReference(value="users-baskets")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @Setter
    private Users users;

    public Baskets(BasketsDTO basketsDTO){
        this.productName= basketsDTO.getProductName();
        this.productStatus= basketsDTO.getProductStatus();
        this.quantity= basketsDTO.getQuantity();
        this.createDate= basketsDTO.getCreateDate();
        this.updateDate= basketsDTO.getUpdateDate();
      //  this.users=basketsDTO.getUsers();
      //  this.products=basketsDTO.getProducts();
    }

}
