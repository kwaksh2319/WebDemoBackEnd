package kr.co.kshproject.webDemo.Domain.Baskets;

import kr.co.kshproject.webDemo.Domain.Products.Products;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketsDTO {

    private String productName;

    private String productStatus;

    private Long quantity;

    private String createDate;

    private String updateDate;

    private Users users;

    private Products products;

    public BasketsDTO(Baskets baskets){
        this.productName=baskets.getProductName();
        this.productStatus=baskets.getProductStatus();
        this.quantity=baskets.getQuantity();
        this.createDate=baskets.getCreateDate();
        this.updateDate=baskets.getUpdateDate();
        this.users=baskets.getUsers();
        this.products=baskets.getProducts();
    }
}
