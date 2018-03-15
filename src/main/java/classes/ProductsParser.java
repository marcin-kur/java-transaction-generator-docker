package classes;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductsParser {

    private FileReaderFactory fileReaderFactory;

    public ProductsParser(FileReaderFactory fileReaderFactory) {
        this.fileReaderFactory = fileReaderFactory;
    }

    public ArrayList<Product> readProducts(Path fileName) throws IOException {
        return fileReaderFactory.getFileLines(fileName).skip(1)
                .map(s -> {
                    String[] splitted = s.split(",");
                    return new Product(splitted[0].replace("\"", ""), new BigDecimal(splitted[1]));
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

