package classes.writers;

import classes.file_factories.FileWriterFactory;
import classes.generators.Item;
import classes.generators.Transaction;
import classes.input_parameters.FileFormat;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileWriterTest {

    @Test
    public void shouldSuccessfullySave() {
        // given
        FileWriterFactory mock = mock(FileWriterFactory.class);
        TestWriter testWriter = new TestWriter(false, false);
        try {
            when(mock.getFileWriter(any(Path.class))).thenReturn(testWriter);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }

        FileFormatWriter fileFormatWriter = FileFormatWriter.getFileFormatWriter(FileFormat.JSON);
        Transaction transaction = new Transaction(1, 1, ZonedDateTime.now(),
                Arrays.asList(new Item("p1", 1, BigDecimal.valueOf(1))));

        FileWriter fileWriter = new FileWriter(Paths.get(""), mock, fileFormatWriter);

        // when
        fileWriter.write(transaction);

        // then
        assertEquals(true, testWriter.successfulWrite);
    }

    @Test
    public void shouldThrowExceptionOnWrite() {
        // given
        FileWriterFactory mock = mock(FileWriterFactory.class);
        TestWriter testWriter = new TestWriter(true, false);
        try {
            when(mock.getFileWriter(any(Path.class))).thenReturn(testWriter);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
        FileFormatWriter fileFormatWriter = FileFormatWriter.getFileFormatWriter(FileFormat.JSON);
        Transaction transaction = new Transaction(1, 1, ZonedDateTime.now(),
                Arrays.asList(new Item("p1", 1, BigDecimal.valueOf(1))));

        FileWriter fileWriter = new FileWriter(Paths.get(""), mock, fileFormatWriter);

        try {
            // when
            fileWriter.write(transaction);

            // then
            assertTrue("FileWriteException should be thrown", false);
        } catch (FileWriteException e) {
            assertTrue("FileWriteException should be thrown", true);
        }
    }

    @Test
    public void shouldThrowExceptionOnWriteAndClose() {
        // given
        FileWriterFactory mock = mock(FileWriterFactory.class);
        TestWriter testWriter = new TestWriter(true, true);
        try {
            when(mock.getFileWriter(any(Path.class))).thenReturn(testWriter);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
        FileFormatWriter fileFormatWriter = FileFormatWriter.getFileFormatWriter(FileFormat.JSON);

        Transaction transaction = new Transaction(1, 1, ZonedDateTime.now(),
                Arrays.asList(new Item("p1", 1, BigDecimal.valueOf(1))));

        FileWriter fileWriter = new FileWriter(Paths.get(""), mock, fileFormatWriter);

        try {
            // when
            fileWriter.write(transaction);

            // then
            assertTrue("FileWriteException should be thrown", false);
        } catch (FileWriteException e) {
            assertTrue("FileWriteException should be thrown", true);
        }
    }

    @Test
    public void shouldThrowExceptionOnClose() {
        // given
        FileWriterFactory mock = mock(FileWriterFactory.class);
        TestWriter testWriter = new TestWriter(false, true);
        try {
            when(mock.getFileWriter(any(Path.class))).thenReturn(testWriter);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
        FileFormatWriter fileFormatWriter = FileFormatWriter.getFileFormatWriter(FileFormat.JSON);

        Transaction transaction = new Transaction(1, 1, ZonedDateTime.now(),
                Arrays.asList(new Item("p1", 1, BigDecimal.valueOf(1))));

        FileWriter fileWriter = new FileWriter(Paths.get(""), mock, fileFormatWriter);

        try {
            // when
            fileWriter.write(transaction);

            // then
            assertTrue("FileWriteException should be thrown", false);
        } catch (FileWriteException e) {
            assertTrue("FileWriteException should be thrown", true);
        }
    }

    private class TestWriter extends Writer {

        public boolean successfulWrite;
        private boolean shouldThrowOnWrite;
        private boolean shouldThrowOnClose;


        TestWriter(boolean shouldThrowExcOnWrite, boolean shouldThrowOnClose) {
            this.shouldThrowOnWrite = shouldThrowExcOnWrite;
            this.shouldThrowOnClose = shouldThrowOnClose;
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            if (shouldThrowOnWrite) {
                throw new IOException();
            }
            successfulWrite = true;
        }

        @Override
        public void flush() throws IOException {
        }

        @Override
        public void close() throws IOException {
            if (shouldThrowOnClose) {
                throw new IOException();
            }
        }
    }
}
