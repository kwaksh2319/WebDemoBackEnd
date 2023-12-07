package kr.co.kshproject.webDemo.Domain.Orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    @Schema(example  = "null")
    private Long id;
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
    @Schema(example = "null")
    private Long userId;

    public OrdersDTO(Orders orders){
        this.id=orders.getId();
        this.payment=orders.getPayment();
        this.status=orders.getStatus();
        this.price=orders.getPrice();
        this.cancel=orders.getCancel();
        this.createdDate=orders.getCreatedDate();
        this.updateDate=orders.getUpdateDate();

        if(orders.getUsers()!=null){
            this.userId=orders.getUsers().getId();
        }
    }
}
