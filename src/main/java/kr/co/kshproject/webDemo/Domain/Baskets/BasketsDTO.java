package kr.co.kshproject.webDemo.Domain.Baskets;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketsDTO {
    @Schema(example  = "null")
    private Long id;
    @Schema(example  = "상품명")
    private String productName;
    @Schema(example  = "상품상태")
    private String productStatus;
    @Schema(example  = "1")
    private Long quantity;
    @Schema(example  = "생성날짜")
    private String createDate;
    @Schema(example  = "업데이트날짜")
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
