package kr.co.kshproject.webDemo.Domain.BeginOrder;

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
    private Products products;
    private Orders orders;
    private Long quantity;

    private String picture;
    private String createdDate;
    private String updateDate;

    public BeginOrderDTO(BeginOrder beginOrder){
        this.products=beginOrder.getProducts();
        this.orders=beginOrder.getOrders();
        this.quantity=beginOrder.getQuantity();
        this.createdDate=beginOrder.getCreatedDate();
        this.updateDate=beginOrder.getUpdateDate();
    }
}
