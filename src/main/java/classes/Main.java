package classes;

import classes.command_lines.CommandLineArgs;
import classes.command_lines.CommandLineDefaultParser;
import classes.file_factories.FileReaderFactory;
import classes.file_factories.FileWriterFactory;
import classes.generators.*;
import classes.input_parameters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started...");

        CommandLineDefaultParser commandLineDefaultParser = new CommandLineDefaultParser();
        CommandLineArgs commandLineArgs = commandLineDefaultParser.parse(args);

        InputParserFactory inputParserFactory = new InputParserFactoryImpl();
        InputParametersCreator inputParametersCreator = new InputParametersCreator(inputParserFactory);
        InputParameters inputParameters = inputParametersCreator.create(commandLineArgs);

        ProductsParser productsParser = new ProductsParser(new FileReaderFactory());
        List<Product> products = productsParser.readProducts(inputParameters.getItemsFile());
        TransactionWriter transactionWriter = new TransactionWriter(inputParameters.getOutDir(), new FileWriterFactory());

        IntegerGenerator customerIdGenerator = new IntegerGenerator(inputParameters.getCustomerIds());
        IntegerGenerator itemCountGenerator = new IntegerGenerator(inputParameters.getItemsCount());
        IntegerGenerator itemQuantityGenerator = new IntegerGenerator(inputParameters.getItemsQuantity());
        TimestampGenerator timestampGenerator = new TimestampGenerator(inputParameters.getDateRange());

        ItemsGenerator itemsGenerator = new ItemsGenerator(itemCountGenerator, itemQuantityGenerator, products);
        TransactionGenerator transactionGenerator = new TransactionGenerator(customerIdGenerator, timestampGenerator, itemsGenerator);

        TransactionsGeneratorHandler transactionsGeneratorHandler = new TransactionsGeneratorHandler(transactionGenerator, transactionWriter, inputParameters.getEventsCount());
        transactionsGeneratorHandler.handle();

        logger.info("Application ended...");
    }
}