package kr.co.kshproject.webDemo.Domain.Products;

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
public class ProductsDTO {
    @Schema(example  = "null")
    private Long id; //key
    @Schema(example  = "상품명")
    @NotNull
    @NotBlank
    private String productName;
    @Schema(example  = "상품설명")
    private String describe;
    @Schema(example  = "1000")
    @Range(min=1000, max=100000000)
    private Long price;
    @Schema(example  = "상품사진")
    @NotBlank
    @NotNull
    private String picture;
    @Schema(example  = "false")
    @NotNull
    private Boolean soldOut;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String createdDate;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
