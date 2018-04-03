package classes.input_parameters;

import classes.command_line.CommandLineArgs;
import classes.generators.IntegerRange;
import classes.generators.TimestampRange;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class InputParametersCreator {

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
        log.info("Input Parameters creation started.", commandLineArgs);
        InputParametersBuilder inputParametersBuilder = new InputParametersBuilder();

        setCustomerIds(inputParametersBuilder, commandLineArgs.getCustomerIds());
        setDateRange(inputParametersBuilder, commandLineArgs.getDateRange());
        setItemsFile(inputParametersBuilder, commandLineArgs.getItemsFile());
        setItemsCount(inputParametersBuilder, commandLineArgs.getItemsCount());
        setItemsQuantity(inputParametersBuilder, commandLineArgs.getItemsQuantity());
        setEventsCount(inputParametersBuilder, commandLineArgs.getEventsCount());
        setOutDir(inputParametersBuilder, commandLineArgs.getOutDir());
        setOutFormat(inputParametersBuilder, commandLineArgs.getFormat());

        return inputParametersBuilder.createInputParameters();
    }

    private void setCustomerIds(InputParametersBuilder inputParametersBuilder, String customerIds) {
        try {
            IntegerRange integerRange = integerRangeParser.parse(getParamValue(customerIds));
            inputParametersBuilder.setCustomerIds(integerRange);
        } catch (InputParseException e) {
            log.warn("Exception during parsing Customer Ids. ", e);
        }
    }

    private void setDateRange(InputParametersBuilder inputParametersBuilder, String dateRange) {
        try {
            TimestampRange timestampRange = timestampParser.parse(getParamValue(dateRange));
            inputParametersBuilder.setDateRange(timestampRange);
        } catch (InputParseException e) {
            log.warn("Exception during parsing Date Range: ", e);
        }
    }

    private void setItemsFile(InputParametersBuilder inputParametersBuilder, String itemsFile) {
        try {
            Path path = pathParser.parseFile(getParamValue(itemsFile));
            inputParametersBuilder.setItemsFile(path);
        } catch (InputParseException e) {
            log.error("Exception during parsing Items File: ", e);
            throw new InputParseException("Exception during parsing Items File: ", e);
        }
    }

    private void setItemsCount(InputParametersBuilder inputParametersBuilder, String itemsCount) {
        try {
            IntegerRange integerRange = integerRangeParser.parse(getParamValue(itemsCount));
            inputParametersBuilder.setItemsCount(integerRange);
        } catch (InputParseException e) {
            log.warn("Exception during parsing Items Count: ", e);
        }
    }

    private void setItemsQuantity(InputParametersBuilder inputParametersBuilder, String itemsQuantity) {
        try {
            IntegerRange integerRange = integerRangeParser.parse(getParamValue(itemsQuantity));
            inputParametersBuilder.setItemsQuantity(integerRange);
        } catch (InputParseException e) {
            log.warn("Exception during parsing Items Quantity: ", e);
        }
    }

    private void setEventsCount(InputParametersBuilder inputParametersBuilder, String eventsCount) {
        try {
            int intValue = integerParser.parse(getParamValue(eventsCount));
            inputParametersBuilder.setEventsCount(intValue);
        } catch (InputParseException e) {
            log.warn("Exception during parsing Event Count: ", e);
        }
    }

    private void setOutDir(InputParametersBuilder inputParametersBuilder, String outDir) {
        try {
            Path path = pathParser.parseDirectory(getParamValue(outDir));
            inputParametersBuilder.setOutDir(path);
        } catch (InputParseException e) {
            log.warn("Exception during parsing Output Directory: ", e);
        }
    }

    private void setOutFormat(InputParametersBuilder inputParametersBuilder, String outFormat) {
        Optional<FileFormat> fileFormat = Arrays.stream(FileFormat.values())
                .filter(f -> f.name().equalsIgnoreCase(outFormat)).findAny();
        if (fileFormat.isPresent()) {
            inputParametersBuilder.setOutFormat(fileFormat.get());
        } else {
            log.warn("Invalid Output Format");
        }
    }

    private String getParamValue(String parameter) {
        return Optional.ofNullable(parameter).orElseThrow(() -> new InputParseException("Empty parameter"));
    }
}