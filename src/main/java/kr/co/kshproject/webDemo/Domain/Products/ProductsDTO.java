package kr.co.kshproject.webDemo.Domain.Products;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
    @Schema(example  = "null")
    private Long id; //key
    @Schema(example  = "상품명")
    private String productName;
    @Schema(example  = "상품설명")
    private String describe;
    @Schema(example  = "1000")
    private Long price;
    @Schema(example  = "상품사진")
    private String picture;
    @Schema(example  = "false")
    private Boolean soldOut;
    @Schema(example  = "생성일")
    private String createdDate;
    @Schema(example  = "업데이트날짜")
    private String updateDate;
    @Schema(example  = "null")
    private Long categoryId;

    public ProductsDTO(Products products){
        this.id=products.getId();
        this.productName=products.getProductName();
        this.describe=products.getDescribe();
        this.picture=products.getPicture();
        this.soldOut=products.getSoldOut();
        this.createdDate=products.getCreatedDate();
        this.updateDate=products.getUpdateDate();
        if(products.getCategory()!=null){
            this.categoryId=products.getCategory().getId();
        }
        this.price=products.getPrice();
    }
}
