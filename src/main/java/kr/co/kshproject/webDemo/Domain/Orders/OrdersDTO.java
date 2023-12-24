package kr.co.kshproject.webDemo.Domain.Orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    @Schema(example  = "null")
    private Long id;
    @Schema(example  = "카드")
    @NotNull
    @NotBlank
    private String payment;
    @Schema(example  = "S")
    @NotNull
    @NotBlank
    private String status;
    @Schema(example  = "10000")
    @Range(min=1000,max=100000000)
    private Long price;

    @Schema(example  = "false")
    @NotNull
    private Boolean cancel;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createdDate;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
