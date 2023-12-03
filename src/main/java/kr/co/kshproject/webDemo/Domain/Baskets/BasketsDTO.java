package kr.co.kshproject.webDemo.Domain.Baskets;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "{'id': 1, 'productName': '카테고리명', 'describe': '생성날짜', 'price': '업데이트날짜', 'picture': '상품사진', 'soldOut': 'false', 'createdDate': '생성일', 'updateDate': '업데이트날짜', 'category': {'id': 1, 'categoryName': '카테고리명', 'createdDate': '생성날짜', 'updateDate': '업데이트날짜', 'parentCategory': 'null', 'products': 'null', 'parentCategories': 'null'}}, 'baskets': 'null', 'beginOrders': 'null'")
    private Products products;
    @Schema(example = "{'id': 1, 'username': '사용자 아이디', 'email': '이메일', 'name': '사용자 이름', 'level': '2', 'password': 'passwords', 'comments': 'null', 'notices': 'null', 'baskets': 'null', 'orders': 'null'}")
    private Users users;

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
