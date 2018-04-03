package classes.input_parameters;

import classes.file_factories.FileReaderFactory;
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

public class ProductReaderTest {
    @Test
    public void shouldReadThreeProductsFromFile() {
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
        ProductsReader productsReader = new ProductsReader(mock);

        //when
        ArrayList<Product> products = productsReader.readProducts(path);

        //then
        assertEquals(3, products.size());
    }

    @Test
    public void shouldReadZeroProductsWhenPriceIsInvalid() {
        // given1
        Path path = Paths.get("someDummyValue");
        FileReaderFactory mock = mock(FileReaderFactory.class);
        try {
            when(mock.getFileLines(path)).then(i -> Stream.of(
                    "name,price",
                    "\"mleko 3% 1l\",mleko")
            );
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }

        ProductsReader productsReader = new ProductsReader(mock);

        try {
            //when
            productsReader.readProducts(path);

            //then
            assertTrue("InputParsException should be thrown", false);
        } catch (InputParseException e) {
            assertTrue("InputParsException should be thrown", true);
        }
    }

    @Test
    public void shouldThrowInputParseExceptionWhenIOExceptionIsThrown() {
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
        ProductsReader productsReader = new ProductsReader(mock);

        try {
            //when
            productsReader.readProducts(path);

            //then
            assertTrue("InputParsException should be thrown", false);
        } catch (InputParseException e) {
            assertTrue("InputParsException should be thrown", true);
        }
    }

    @Test
    public void shouldThrowInputParseExceptionWhenFileIsEmpty() {
        // given
        Path path = Paths.get("someDummyValue");
        FileReaderFactory mock = mock(FileReaderFactory.class);
        try {
            when(mock.getFileLines(path)).then(i -> "");
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }

        ProductsReader productsReader = new ProductsReader(mock);

        //when
        try {
            ArrayList<Product> products = productsReader.readProducts(path);

            //then
            assertTrue("InputParseException should be thrown", false);
        } catch (InputParseException e) {
            assertTrue("InputParseException should be thrown", true);
        }
    }

    @Test
    public void shouldThrowExceptionWhenFileHasOnlyHeader() {
        // given
        Path path = Paths.get("someDummyV2alue");
        FileReaderFactory mock = mock(FileReaderFactory.class);
        try {
            when(mock.getFileLines(path)).then(i -> Stream.of(
                    "name,price")
            );
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
        ProductsReader productsReader = new ProductsReader(mock);

        try {
            //when
            ArrayList<Product> products = productsReader.readProducts(path);

            // then
            assertTrue("InputParseException should be thrown", false);
        } catch (InputParseException e) {
            assertTrue("InputParseException should be thrown", true);
        }
    }

    @Test
    public void shouldThrowExceptionWhenPriceIsEmpty() {
        // given
        Path path = Paths.get("someDummyValue");
        FileReaderFactory mock = mock(FileReaderFactory.class);
        try {
            when(mock.getFileLines(path)).then(i -> Stream.of(
                    "name,price",
                    "\"mleko 3% 1l\"")
            );
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
        ProductsReader productsReader = new ProductsReader(mock);

        try {
            //when
            productsReader.readProducts(path);

            //then
            assertTrue("InputParsException should be thrown", false);
        } catch (InputParseException e) {
            assertTrue("InputParsException should be thrown", true);
        }
    }

    @Test
    public void mixTest() {
        // given
        Path path = Paths.get("someDummyValue");
        FileReaderFactory mock = mock(FileReaderFactory.class);
        try {
            when(mock.getFileLines(path)).then(i -> Stream.of(
                    "name,price",
                    "\"mleko 3% 1l\",2.30",   // valid
                    "\"bułeczka\"",           // invalid
                    "\"chleb biały\",2x20",   // invalid
                    "\"bułeczka\",1.20",      // valid
                    "\"chleb biały\",2.20")   // valid
            );
        } catch (IOException e) {
            assertTrue("IOException shouldn't be thrown", false);
        }
        ProductsReader productsReader = new ProductsReader(mock);

        //when
        ArrayList<Product> products = productsReader.readProducts(path);

        //then
        assertEquals(3, products.size());
    }
}
