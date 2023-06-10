package kr.co.kshproject.webDemo.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketId implements Serializable {

    @NotNull
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @NotNull
    @Column(name = "USERS_ID")
    private String usersId;

    @NotNull
    @Column(name = "DATE")
    private String date;

}
