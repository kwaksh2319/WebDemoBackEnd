package kr.co.kshproject.webDemo.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketsWithProduct {
   private Baskets baskets;
   private Product product;
   public BasketsWithProduct(Baskets baskets, Product product) {
      this.baskets = baskets;
      this.product = product;
   }
}
