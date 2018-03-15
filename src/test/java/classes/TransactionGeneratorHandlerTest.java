package classes;

import org.junit.Test;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionGeneratorHandlerTest {

    private final TestUtils testUtils = new TestUtils();

    @Test
    public void shouldGenerateJsonFiles() {
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

        FileWriterFactory mock = mock(FileWriterFactory.class);
        TestWriter testWriter = new TestWriter();
        try {
            when(mock.getFileWriter(any(Path.class))).thenReturn(testWriter);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }

        TransactionWriter transactionWriter = new TransactionWriter(Paths.get("/"), mock);
        TransactionsGeneratorHandler transactionsGeneratorHandler = new TransactionsGeneratorHandler(transactionGenerator, transactionWriter, 100);

        // when
        try {
            transactionsGeneratorHandler.handle();

            // then
            assertEquals(100, testWriter.count);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }


    }

    private class TestWriter extends Writer {

        public int count = 0;

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            count++;
        }

        @Override
        public void flush() throws IOException {}

        @Override
        public void close() throws IOException {}
    }
}
