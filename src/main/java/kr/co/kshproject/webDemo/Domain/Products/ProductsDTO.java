package kr.co.kshproject.webDemo.Domain.Products;

import kr.co.kshproject.webDemo.Domain.Category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
    private String productName;

    private String describe;

    private Long price;

    private String picture;

    private Boolean soldOut;

    private String createdDate;

    private String updateDate;

    private Category category;

    public ProductsDTO(Products products){
        this.productName=products.getProductName();
        this.describe=products.getDescribe();
        this.picture=products.getPicture();
        this.soldOut=products.getSoldOut();
        this.createdDate=products.getCreatedDate();
        this.updateDate=products.getUpdateDate();
        this.category=products.getCategory();
        this.price=products.getPrice();
    }
}
