package kr.co.kshproject.webDemo.Domain.Orders;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example  = "카드")
    private String payment;
    @Schema(example  = "S")
    private String status;
    @Schema(example  = "10000")
    private Long price;
    @Schema(example  = "false")
    private Boolean cancel;
    @Schema(example  = "생성일자")
    private String createdDate;
    @Schema(example  = "업데이트날짜")
    private String updateDate;
    @Schema(example = "{'id': 1, 'username': '사용자 아이디', 'email': '이메일', 'name': '사용자 이름', 'level': '2', 'password': 'passwords', 'comments': 'null', 'notices': 'null', 'baskets': 'null', 'orders': 'null'}")
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
