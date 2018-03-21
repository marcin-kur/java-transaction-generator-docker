package classes.input_parameters;

import classes.command_lines.CommandLineArgs;
import classes.generators.IntegerRange;
import classes.generators.TimestampRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.Optional;

public class InputParametersCreator {

    private static Logger logger = LoggerFactory.getLogger(InputParametersCreator.class);

    private final InputIntegerParser integerParser;
    private final InputIntegerRangeParser integerRangeParser;
    private final InputTimestampRangeParser timestampParser;
    private final InputPathParser pathParser;

    public InputParametersCreator(InputParserFactory inputParserFactory) {
        this.integerParser = inputParserFactory.createIntegerParser();
        this.integerRangeParser = inputParserFactory.createIntegerRangeParser();
        this.timestampParser = inputParserFactory.createTimestampRangeParser();
        this.pathParser = inputParserFactory.createPathParser();
    }

    public InputParameters create(CommandLineArgs commandLineArgs) {
        logger.info("Input Parameters creation started. CommandLineArgs: " + commandLineArgs);
        InputParametersBuilder inputParametersBuilder = new InputParametersBuilder();

        setCustomerIds(inputParametersBuilder, commandLineArgs.getCustomerIds());
        setDateRange(inputParametersBuilder, commandLineArgs.getDateRange());
        setItemsFile(inputParametersBuilder, commandLineArgs.getItemsFile());
        setItemsCount(inputParametersBuilder, commandLineArgs.getItemsCount());
        setItemsQuantity(inputParametersBuilder, commandLineArgs.getItemsQuantity());
        setEventsCount(inputParametersBuilder, commandLineArgs.getEventsCount());
        setOutDir(inputParametersBuilder, commandLineArgs.getOutDir());

        return inputParametersBuilder.createInputParameters();
    }

    private void setCustomerIds(InputParametersBuilder inputParametersBuilder, String customerIds) {
        try {
            IntegerRange integerRange = integerRangeParser.parse(getParamValue(customerIds));
            inputParametersBuilder.setCustomerIds(integerRange);
        } catch (InputParseException e) {
            logger.warn("Exception during parsing Customer Ids: " + e.getMessage());
        }
    }

    private void setDateRange(InputParametersBuilder inputParametersBuilder, String dateRange) {
        try {
            TimestampRange timestampRange = timestampParser.parse(getParamValue(dateRange));
            inputParametersBuilder.setDateRange(timestampRange);
        } catch (InputParseException e) {
            logger.warn("Exception during parsing Date Range: " + e.getMessage());
        }
    }

    private void setItemsFile(InputParametersBuilder inputParametersBuilder, String itemsFile) {
        try {
            Path path = pathParser.parseFile(getParamValue(itemsFile));
            inputParametersBuilder.setItemsFile(path);
        } catch (InputParseException e) {
            logger.error("Exception during parsing Items File: " + e.getMessage());
            throw new InputParseException("Exception during parsing Items File: " + e.getMessage());
        }
    }

    private void setItemsCount(InputParametersBuilder inputParametersBuilder, String itemsCount) {
        try {
            IntegerRange integerRange = integerRangeParser.parse(getParamValue(itemsCount));
            inputParametersBuilder.setItemsCount(integerRange);
        } catch (InputParseException e) {
            logger.warn("Exception during parsing Items Count: " + e.getMessage());
        }
    }

    private void setItemsQuantity(InputParametersBuilder inputParametersBuilder, String itemsQuantity) {
        try {
            IntegerRange integerRange = integerRangeParser.parse(getParamValue(itemsQuantity));
            inputParametersBuilder.setItemsQuantity(integerRange);
        } catch (InputParseException e) {
            logger.warn("Exception during parsing Items Quantity: " + e.getMessage());
        }
    }

    private void setEventsCount(InputParametersBuilder inputParametersBuilder, String eventsCount) {
        try {
            int intValue = integerParser.parse(getParamValue(eventsCount));
            inputParametersBuilder.setEventsCount(intValue);
        } catch (InputParseException e) {
            logger.warn("Exception during parsing Event Count: " + e.getMessage());
        }
    }

    private void setOutDir(InputParametersBuilder inputParametersBuilder, String outDir) {
        try {
            Path path = pathParser.parseDirectory(getParamValue(outDir));
            inputParametersBuilder.setOutDir(path);
        } catch (InputParseException e) {
            logger.warn("Exception during parsing Out Directory: " + e.getMessage());
        }
    }

    private String getParamValue(String parameter) {
        return Optional.ofNullable(parameter).orElseThrow(() -> new InputParseException("Empty parameter"));
    }
}
