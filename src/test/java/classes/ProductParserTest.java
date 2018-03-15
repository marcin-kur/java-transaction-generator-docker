package classes;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductParserTest {
    @Test
    public void shouldReadProductsFromFile() {
        // given
        Path path = Paths.get("someDummyValue");
        FileReaderFactory mock = mock(FileReaderFactory.class);
        try {
            when(mock.getFileLines(path)).then(i -> Stream.of(
                    "name,price",
                    "\"mleko 3% 1l\",2.30",
                    "\"bułeczka\",1.20",
                    "\"chleb biały\",2.20")
            );
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
        ProductsParser productsParser = new ProductsParser(mock);

        //when
        try {
            ArrayList<Product> products = productsParser.readProducts(path);

            //then
            assertEquals(3, products.size());
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
    }

    @Test
    public void shouldThrowNumberFormatException() {
        // given
        Path path = Paths.get("someDummyValue");
        FileReaderFactory mock = mock(FileReaderFactory.class);
        try {

            when(mock.getFileLines(path)).then(i -> Stream.of(
                    "name,price",
                    "\"mleko 3% 1l\",sasasa",
                    "\"bułeczka\",1.20",
                    "\"chleb biały\",2.20")
            );
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }

        ProductsParser productsParser = new ProductsParser(mock);

        //when
        try {
            ArrayList<Product> products = productsParser.readProducts(path);

            //then
            assertTrue("NumberFormatException should be thrown", false);
        } catch (NumberFormatException e) {
            assertTrue("NumberFormatException should be thrown", true);
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
    }

    @Test
    public void shouldThrowIOException() {
        // given
        Path path = Paths.get("someDummyValue");
        FileReaderFactory mock = mock(FileReaderFactory.class);

        try {
            when(mock.getFileLines(path)).then(i -> {
                throw new IOException();
            });
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
        ProductsParser productsParser = new ProductsParser(mock);

        //when
        try {
            ArrayList<Product> products = productsParser.readProducts(path);

            //then
            assertTrue("IOException should be thrown", false);
        } catch (IOException e) {
            assertTrue("IOException should be thrown", true);
        }
    }
}
