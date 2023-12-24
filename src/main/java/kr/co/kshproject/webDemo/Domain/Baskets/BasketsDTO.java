package kr.co.kshproject.webDemo.Domain.Baskets;

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
@NoArgsConstructor
@AllArgsConstructor
public class BasketsDTO {
    @Schema(example  = "null")
    private Long id;
    @Schema(example  = "상품명")
    @NotNull
    @NotBlank
    private String productName;
    @Schema(example  = "상품상태")
    @NotNull
    @NotBlank
    private String productStatus;
    @Schema(example  = "1")
    @Range(min=1,max=1000)
    private Long quantity;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createDate;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String updateDate;

    @Schema(example = "null")
    private Long productId;

    @Schema(example = "null")
    private Long userId;

    public BasketsDTO(Baskets baskets){
        this.id=baskets.getId();
        this.productName=baskets.getProductName();
        this.productStatus=baskets.getProductStatus();
        this.quantity=baskets.getQuantity();
        this.createDate=baskets.getCreateDate();
        this.updateDate=baskets.getUpdateDate();
        if(baskets.getProducts()!=null){
            this.productId=baskets.getProducts().getId();
        }
        if(baskets.getUsers()!=null){
            this.userId=baskets.getUsers().getId();
        }

    }
}
