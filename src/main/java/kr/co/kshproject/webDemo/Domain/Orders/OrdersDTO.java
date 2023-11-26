package kr.co.kshproject.webDemo.Domain.Orders;

import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {

    private String payment;

    private String status;

    private Long price;

    private Boolean cancel;

    private String createdDate;

    private String updateDate;

    private Users users;
    public OrdersDTO(Orders orders){
        this.payment=orders.getPayment();
        this.status=orders.getStatus();
        this.price=orders.getPrice();
        this.cancel=orders.getCancel();
        this.createdDate=orders.getCreatedDate();
        this.updateDate=orders.getUpdateDate();
        this.users=orders.getUsers();
    }
}
