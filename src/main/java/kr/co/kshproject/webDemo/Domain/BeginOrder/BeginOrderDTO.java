package kr.co.kshproject.webDemo.Domain.BeginOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.kshproject.webDemo.Domain.Orders.Orders;
import kr.co.kshproject.webDemo.Domain.Products.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeginOrderDTO {
    @Schema(example = "{'id': 1, 'productName': '카테고리명', 'describe': '생성날짜', 'price': '업데이트날짜', 'picture': '상품사진', 'soldOut': 'false', 'createdDate': '생성일', 'updateDate': '업데이트날짜', 'category': {'id': 1, 'categoryName': '카테고리명', 'createdDate': '생성날짜', 'updateDate': '업데이트날짜', 'parentCategory': 'null', 'products': 'null', 'parentCategories': 'null'}}, 'baskets': 'null', 'beginOrders': 'null'")
    private Products products;
    @Schema(example = "{'id': 1, 'payment': '카드', 'status': 'S', 'price': '10000', 'cancel': 'false', 'createdDate': '생성일자', 'updateDate': '업데이트날짜', 'users': {'id': 1, 'username': '사용자 아이디', 'email': '이메일', 'name': '사용자 이름', 'level': '2', 'password': 'passwords', 'comments': 'null', 'notices': 'null', 'baskets': 'null', 'orders': 'null'}, 'beginOrders': 'null'}")
    private Orders orders;
    @Schema(example  = "1")
    private Long quantity;
    @Schema(example  = "사진경로")
    private String picture;
    @Schema(example  = "생성날짜")
    private String createdDate;
    @Schema(example  = "업데이트날짜")
    private String updateDate;

    public BeginOrderDTO(BeginOrder beginOrder){
        this.products=beginOrder.getProducts();
        this.orders=beginOrder.getOrders();
        this.quantity=beginOrder.getQuantity();
        this.createdDate=beginOrder.getCreatedDate();
        this.updateDate=beginOrder.getUpdateDate();
    }
}
