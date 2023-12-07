package kr.co.kshproject.webDemo.Domain.Orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrder;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    @SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @NotEmpty
    @Setter
    private String payment;

    @NotEmpty
    @Setter
    private String status;

    @NonNull
    @Setter
    private Long price;

    @NonNull
    @Setter
    private Boolean cancel;

    @NotEmpty
    @Setter
    @Column(name = "created_date")
    private String createdDate;

    @NotEmpty
    @Setter
    @Column(name = "update_date")
    private String updateDate;

    @JsonBackReference(value="users-orders")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @Setter
    private Users users;

    @JsonManagedReference(value="orders-beginOrders")
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BeginOrder> beginOrders = new ArrayList<>();

    public Orders(OrdersDTO ordersDTO){
        this.payment=ordersDTO.getPayment();
        this.status= ordersDTO.getStatus();
        this.price= ordersDTO.getPrice();
        this.cancel=ordersDTO.getCancel();
        this.createdDate= ordersDTO.getCreatedDate();
        this.updateDate= ordersDTO.getUpdateDate();
       // this.users=ordersDTO.getUsers();
    }

}
