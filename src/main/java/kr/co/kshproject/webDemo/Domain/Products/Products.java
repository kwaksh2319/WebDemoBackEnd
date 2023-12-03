package kr.co.kshproject.webDemo.Domain.Products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.co.kshproject.webDemo.Domain.Baskets.Baskets;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrder;
import kr.co.kshproject.webDemo.Domain.Category.Category;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
@Slf4j
@Entity
@Table(name = "Products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    @SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @NotEmpty
    @Setter
    private String productName;

    @NotEmpty
    @Setter
    private String describe;

    @NotNull
    @Setter
    private Long price;

    @NotEmpty
    @Setter
    private String picture;

    @NotNull
    @Setter
    @Column(name = "sold_out")
    private Boolean soldOut;

    @NotEmpty
    @Setter
    @Column(name = "created_date")
    private String createdDate;

    @NotEmpty
    @Setter
    @Column(name = "update_date")
    private String updateDate;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable = true)
    @Setter
    private Category category;

    @JsonManagedReference(value="products-baskets")
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Baskets> baskets = new HashSet<>();

    @JsonManagedReference(value="products-beginOrders")
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BeginOrder> beginOrders = new HashSet<>();

    public Products(ProductsDTO productsDTO){
        this.productName=productsDTO.getProductName();
        this.describe=productsDTO.getDescribe();
        this.price=productsDTO.getPrice();
        this.picture=productsDTO.getPicture();
        this.soldOut=productsDTO.getSoldOut();
        this.createdDate=productsDTO.getCreatedDate();
        this.updateDate=productsDTO.getUpdateDate();
        //this.category=productsDTO.getCategory();
    }
}
