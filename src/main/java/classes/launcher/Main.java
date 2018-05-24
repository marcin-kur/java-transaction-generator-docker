package classes.launcher;

import classes.properties.AppProperties;
import classes.file_factories.FileReaderFactory;
import classes.file_factories.FileWriterFactory;
import classes.generators.*;
import classes.input_parameters.*;
import classes.model.Product;
import classes.properties.PropertiesReader;
import classes.writers.FileFormatWriter;
import classes.writers.FileWriter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Application started...");

        PropertiesReader propertiesReader = new PropertiesReader();
        AppProperties appProperties = propertiesReader.readProperties();

        InputParserFactory inputParserFactory = new InputParserFactory();
        InputParametersCreator inputParametersCreator = new InputParametersCreator(inputParserFactory);
        InputParameters inputParameters = inputParametersCreator.create(appProperties);

        ProductsReader productsReader = new ProductsReader(new FileReaderFactory());
        List<Product> products = productsReader.readProducts(inputParameters.getItemsFile());

        IntegerGenerator customerIdGenerator = new IntegerGenerator(inputParameters.getCustomerIds());
        IntegerGenerator itemCountGenerator = new IntegerGenerator(inputParameters.getItemsCount());
        IntegerGenerator itemQuantityGenerator = new IntegerGenerator(inputParameters.getItemsQuantity());
        TimestampGenerator timestampGenerator = new TimestampGenerator(inputParameters.getDateRange());
        ItemsGenerator itemsGenerator = new ItemsGenerator(itemCountGenerator, itemQuantityGenerator, products);
        TransactionGenerator transactionGenerator = new TransactionGenerator(customerIdGenerator, timestampGenerator, itemsGenerator);

        FileFormatWriter fileFormatWriter = FileFormatWriter.getFileFormatWriter(inputParameters.getOutFormat());
        FileWriter fileWriter = new FileWriter(inputParameters.getOutDir(), new FileWriterFactory(), fileFormatWriter);

        TransactionsGenerator transactionsGenerator = new TransactionsGenerator(transactionGenerator, fileWriter, inputParameters.getEventsCount());
        transactionsGenerator.generate();

        log.info("Application ended...");
    }
}