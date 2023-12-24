package kr.co.kshproject.webDemo.Domain.BeginOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeginOrderDTO {
    @Schema(example  = "null")
    private Long id;
    @Schema(example  = "1")
    @Range(min=1,max=100000)
    private Long quantity;

    @Schema(example  = "사진경로")
    private String picture;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createdDate;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String updateDate;

    @Schema(example = "null")
    private Long productId;
    @Schema(example = "null")
    private Long orderId;

    public BeginOrderDTO(BeginOrder beginOrder){
        this.id=beginOrder.getId();
        this.quantity=beginOrder.getQuantity();
        this.createdDate=beginOrder.getCreatedDate();
        this.updateDate=beginOrder.getUpdateDate();
        if(beginOrder.getProducts()!=null){
            this.productId=beginOrder.getProducts().getId();
        }
        if(beginOrder.getOrders()!=null){
            this.orderId=beginOrder.getOrders().getId();
        }

    }
}
