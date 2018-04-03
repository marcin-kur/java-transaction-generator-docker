package classes.generators;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@ToString
public class Transaction {
    public int id;

    @JsonProperty("customer_id")
    private int customerId;

    private ZonedDateTime timestamp;

    private List<Item> items;

    private BigDecimal sum;

    public Transaction(int id, int customerId, ZonedDateTime timestamp, List<Item> items) {
        this.id = id;
        this.customerId = customerId;
        this.timestamp = timestamp;
        this.items = items;
        this.sum = calculateSum();
    }

    private BigDecimal calculateSum() {
        return items
                .stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
