package classes.generators;

import classes.TestUtils;
import classes.file_factories.FileWriterFactory;
import classes.input_parameters.FileFormat;
import classes.model.Product;
import classes.writers.FileFormatWriter;
import classes.writers.FileWriteException;
import classes.writers.FileWriter;
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
        IntegerGenerator itemCountGenerator = new IntegerGenerator(new Range<>(100, 150));
        IntegerGenerator itemQuantityGenerator = new IntegerGenerator(new Range<>(5, 10));
        ItemsGenerator itemsGenerator = new ItemsGenerator(itemCountGenerator, itemQuantityGenerator, products);
        IntegerGenerator customerIdGenerator = new IntegerGenerator(new Range<>(50, 100));
        TimestampGenerator timestampGenerator = new TimestampGenerator(new Range<>(testUtils.getBeginOfToday(), testUtils.getEndOfToday()));

        TransactionGenerator transactionGenerator = new TransactionGenerator(customerIdGenerator, timestampGenerator, itemsGenerator);
        FileFormatWriter fileFormatWriter = FileFormatWriter.getFileFormatWriter(FileFormat.JSON);

        FileWriterFactory mock = mock(FileWriterFactory.class);
        TestWriter testWriter = new TestWriter(false);
        try {
            when(mock.getFileWriter(any(Path.class))).thenReturn(testWriter);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }

        FileWriter fileWriter = new FileWriter(Paths.get("/"), mock, fileFormatWriter);
        TransactionsGenerator transactionsGenerator = new TransactionsGenerator(transactionGenerator, fileWriter, 100);

        // when
        transactionsGenerator.generate();

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
        IntegerGenerator itemCountGenerator = new IntegerGenerator(new Range<>(100, 150));
        IntegerGenerator itemQuantityGenerator = new IntegerGenerator(new Range<>(5, 10));
        ItemsGenerator itemsGenerator = new ItemsGenerator(itemCountGenerator, itemQuantityGenerator, products);
        IntegerGenerator customerIdGenerator = new IntegerGenerator(new Range<>(50, 100));
        TimestampGenerator timestampGenerator = new TimestampGenerator(new Range<>(testUtils.getBeginOfToday(), testUtils.getEndOfToday()));

        TransactionGenerator transactionGenerator = new TransactionGenerator(customerIdGenerator, timestampGenerator, itemsGenerator);
        FileFormatWriter fileFormatWriter = FileFormatWriter.getFileFormatWriter(FileFormat.JSON);

        FileWriterFactory mock = mock(FileWriterFactory.class);
        TestWriter testWriter = new TestWriter(true);
        try {
            when(mock.getFileWriter(any(Path.class))).thenReturn(testWriter);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
        FileWriter fileWriter = new FileWriter(Paths.get("/"), mock, fileFormatWriter);
        TransactionsGenerator transactionsGenerator = new TransactionsGenerator(transactionGenerator, fileWriter, 100);

        try {
            // when
            transactionsGenerator.generate();

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
