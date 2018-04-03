package classes.input_parameters;

import classes.file_factories.FileReaderFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ProductsReader {

    private FileReaderFactory fileReaderFactory;

    public ProductsReader(FileReaderFactory fileReaderFactory) {
        this.fileReaderFactory = fileReaderFactory;
    }

    public ArrayList<Product> readProducts(Path fileName) {
        log.info("Product parsing started. Filename: " + fileName);

        try (Stream<String> lines = fileReaderFactory.getFileLines(fileName)) {
            return validateProducts(getProducts(lines));
        } catch (IOException | ClassCastException e) {
            log.error("Error during parsing Products. " + e.getMessage());
            throw new InputParseException(e.getMessage());
        }
    }

    private ArrayList<Product> validateProducts(ArrayList<Product> products) {
        if (products.isEmpty()) {
            throw new InputParseException("There are no valid products");
        }

        return products;
    }

    private ArrayList<Product> getProducts(Stream<String> lines) {
        return lines.skip(1)
                .map(this::parseProduct)
                .filter(p -> Optional.ofNullable(p).isPresent())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Product parseProduct(String line) {
        try {
            String[] elements = line.split(",", 2);
            return new Product(elements[0], new BigDecimal(elements[1]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            log.error("Error during parsing Product: " + line + ". Error: " + e.getMessage());
            return null;
        }
    }
}