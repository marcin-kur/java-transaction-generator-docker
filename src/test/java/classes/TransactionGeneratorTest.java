package classes;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionGeneratorTest {

    private final TestUtils testUtils = new TestUtils();

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
        IntegerGenerator customerIdGenerator = new IntegerGenerator(new IntegerRange(50, 100));
        TimestampGenerator timestampGenerator = new TimestampGenerator(new TimestampRange(testUtils.getBeginOfToday(), testUtils.getEndOfToday()));

        TransactionGenerator transactionGenerator = new TransactionGenerator(customerIdGenerator, timestampGenerator, itemsGenerator);

        //when
        Stream<Transaction> transactionStream = IntStream.range(1, 100).mapToObj(transactionGenerator::generate);

        //then
        transactionStream.forEach(
                transaction -> {
                    assertTrue("Validate id failed:" + transaction.getId(),
                            transaction.getId() >= 1 && transaction.getId() <= 100);
                    assertTrue("Validate customerId failed:" + transaction.getCustomerId(),
                            transaction.getCustomerId() >= 50 && transaction.getCustomerId() <= 100);
                    assertTrue("Validate timestamp failed: " + transaction.getTimestamp(), transaction.getTimestamp().isAfter(testUtils.getBeginOfToday()));
                    assertTrue("Validate timestamp failed: " + transaction.getTimestamp(), transaction.getTimestamp().isBefore(testUtils.getEndOfToday()));
                    assertTrue("Validate timestamp failed: " + transaction.getTimestamp(), transaction.getTimestamp().isBefore(testUtils.getEndOfToday()));
                }
        );
    }
}
