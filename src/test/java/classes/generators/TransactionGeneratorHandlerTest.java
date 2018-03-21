package classes.generators;

import classes.TestUtils;
import classes.file_factories.FileWriterFactory;
import classes.input_parameters.Product;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
        List<Product> products = Arrays.asList(
                new Product("p1", new BigDecimal(1)),
                new Product("p2", new BigDecimal(2)),
                new Product("p3", new BigDecimal(3))
        );
        IntegerGenerator itemCountGenerator = new IntegerGenerator(new IntegerRange(100, 150));
        IntegerGenerator itemQuantityGenerator = new IntegerGenerator(new IntegerRange(5, 10));
        ItemsGenerator itemsGenerator = new ItemsGenerator(itemCountGenerator, itemQuantityGenerator, products);
        IntegerGenerator customerIdGenerator = new IntegerGenerator(new IntegerRange(50, 100));
        TimestampGenerator timestampGenerator = new TimestampGenerator(new TimestampRange(testUtils.getBeginOfToday(), testUtils.getEndOfToday()));

        TransactionGenerator transactionGenerator = new TransactionGenerator(customerIdGenerator, timestampGenerator, itemsGenerator);

        FileWriterFactory mock = mock(FileWriterFactory.class);
        TestWriter testWriter = new TestWriter(false);
        try {
            when(mock.getFileWriter(any(Path.class))).thenReturn(testWriter);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }

        TransactionWriter transactionWriter = new TransactionWriter(Paths.get("/"), mock);
        TransactionsGeneratorHandler transactionsGeneratorHandler = new TransactionsGeneratorHandler(transactionGenerator, transactionWriter, 100);

        // when
        transactionsGeneratorHandler.handle();

        // then
        assertEquals(100, testWriter.count);
    }

    @Test
    public void shouldThrowFileWriteException() {
        // given
        List<Product> products = Arrays.asList(
                new Product("p1", new BigDecimal(1)),
                new Product("p2", new BigDecimal(2)),
                new Product("p3", new BigDecimal(3))
        );
        IntegerGenerator itemCountGenerator = new IntegerGenerator(new IntegerRange(100, 150));
        IntegerGenerator itemQuantityGenerator = new IntegerGenerator(new IntegerRange(5, 10));
        ItemsGenerator itemsGenerator = new ItemsGenerator(itemCountGenerator, itemQuantityGenerator, products);
        IntegerGenerator customerIdGenerator = new IntegerGenerator(new IntegerRange(50, 100));
        TimestampGenerator timestampGenerator = new TimestampGenerator(new TimestampRange(testUtils.getBeginOfToday(), testUtils.getEndOfToday()));

        TransactionGenerator transactionGenerator = new TransactionGenerator(customerIdGenerator, timestampGenerator, itemsGenerator);

        FileWriterFactory mock = mock(FileWriterFactory.class);
        TestWriter testWriter = new TestWriter(true);
        try {
            when(mock.getFileWriter(any(Path.class))).thenReturn(testWriter);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }

        TransactionWriter transactionWriter = new TransactionWriter(Paths.get("/"), mock);
        TransactionsGeneratorHandler transactionsGeneratorHandler = new TransactionsGeneratorHandler(transactionGenerator, transactionWriter, 100);

        try {
            // when
            transactionsGeneratorHandler.handle();

            // then
            assertTrue("FileWriteException should be thrown", false);
        } catch (FileWriteException e) {
            assertTrue("FileWriteException should be thrown", true);
        }
    }

    private class TestWriter extends Writer {

        private boolean shouldThrowExcOnWrite;

        public int count = 0;

        TestWriter(boolean shouldThrowExcOnWrite) {
            this.shouldThrowExcOnWrite = shouldThrowExcOnWrite;
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            count++;
            if (shouldThrowExcOnWrite) {
                throw new IOException();
            }
        }

        @Override
        public void flush() throws IOException {
        }

        @Override
        public void close() throws IOException {
        }
    }
}
