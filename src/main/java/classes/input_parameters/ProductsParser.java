package classes.input_parameters;

import classes.file_factories.FileReaderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductsParser {

    private static final Logger logger = LoggerFactory.getLogger(ProductsParser.class);

    private FileReaderFactory fileReaderFactory;

    public ProductsParser(FileReaderFactory fileReaderFactory) {
        this.fileReaderFactory = fileReaderFactory;
    }

    public ArrayList<Product> readProducts(Path fileName) {
        logger.info("Product parsing started. Filename: " + fileName);
        try (Stream<String> lines = fileReaderFactory.getFileLines(fileName)) {
            return lines.skip(1)
                    .map(this::parseProduct)
                    .filter(p -> Optional.ofNullable(p).isPresent())
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException | NullPointerException e) {
            logger.error("Error during parsing Products. " + e.getMessage());
            throw new InputParseException(e.getMessage());
        }
    }

    private Product parseProduct(String line) {
        try {
            String[] elements = line.split(",", 2);
            return new Product(elements[0], new BigDecimal(elements[1]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}


