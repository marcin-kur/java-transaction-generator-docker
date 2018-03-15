package classes;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            DefaultCommandLineParser defaultCommandLineParser = new DefaultCommandLineParser();
            CommandLineArgs commandLineArgs = defaultCommandLineParser.parse(args);

            InputIntegerParser integerParser = new InputIntegerParser();
            InputIntegerRangeParser integerRangeParser = new InputIntegerRangeParser();
            InputTimestampRangeParser timestampParser = new InputTimestampRangeParser();
            InputPathParser pathParser = new InputPathParser();

            InputValueFactory inputValueFactory = new InputValueFactory(integerParser, integerRangeParser, timestampParser, pathParser);

            InputParametersCreator inputParametersCreator = new InputParametersCreator(inputValueFactory);
            InputParameters inputParameters = inputParametersCreator.create(commandLineArgs);

            ProductsParser productsParser = new ProductsParser(new FileReaderFactory());
            ArrayList<Product> products = productsParser.readProducts(inputParameters.getItemsFile());
            TransactionWriter transactionWriter = new TransactionWriter(inputParameters.getOutDir(), new FileWriterFactory());

            IntegerGenerator customerIdGenerator = new IntegerGenerator(inputParameters.getCustomerIds());
            IntegerGenerator itemCountGenerator = new IntegerGenerator(inputParameters.getItemsCount());
            IntegerGenerator itemQuantityGenerator = new IntegerGenerator(inputParameters.getItemsQuantity());
            TimestampGenerator timestampGenerator = new TimestampGenerator(inputParameters.getDateRange());

            ItemsGenerator itemsGenerator = new ItemsGenerator(itemCountGenerator, itemQuantityGenerator, products);
            TransactionGenerator transactionGenerator = new TransactionGenerator(customerIdGenerator, timestampGenerator, itemsGenerator);

            TransactionsGeneratorHandler transactionsGeneratorHandler = new TransactionsGeneratorHandler(transactionGenerator, transactionWriter, inputParameters.getEventsCount());
            transactionsGeneratorHandler.handle();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}