package classes;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ItemsGeneratorTest {
    @Test
    public void shouldReadProductsFromFile() {
        // given
        ArrayList<Product> products = new ArrayList<>(Arrays.asList(
                new Product("p1", new BigDecimal(1)),
                new Product("p2", new BigDecimal(2)),
                new Product("p3", new BigDecimal(3))
        ));
        IntegerGenerator itemCountGenerator = new IntegerGenerator(new IntegerRange(100, 150));
        IntegerGenerator itemQuantityGenerator = new IntegerGenerator(new IntegerRange(5, 10));
        ItemsGenerator itemsGenerator = new ItemsGenerator(itemCountGenerator, itemQuantityGenerator, products);

        //when
        List<Item> items = itemsGenerator.generate();
        //then
        assertTrue("Validate items size failed: " + items.size(), items.size() >= 100 && items.size() <= 150);
        for (Item item : items) {
            assertTrue("Validate quantity failed:" + item.getQuantity(),
                    item.getQuantity() >= 5 && item.getQuantity() <= 10);
        }
    }
}
