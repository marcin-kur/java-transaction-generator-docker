package classes.generators;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Item {
    private final String name;
    private final int quantity;
    private final BigDecimal price;

    public Item(String name, int quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }

}
