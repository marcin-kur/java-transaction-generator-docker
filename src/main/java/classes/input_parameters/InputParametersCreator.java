package classes.input_parameters;

import classes.properties.Properties;
import classes.generators.Range;
import classes.parsers.ParseException;
import classes.parsers.IntegerParser;
import classes.parsers.IntegerRangeParser;
import classes.parsers.TimestampRangeParser;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class InputParametersCreator {

    private final IntegerParser integerParser;
    private final IntegerRangeParser integerRangeParser;
    private final TimestampRangeParser timestampParser;
    private final InputPathParser pathParser;

    public InputParametersCreator(InputParserFactory inputParserFactory) {
        this.integerParser = inputParserFactory.createIntegerParser();
        this.integerRangeParser = inputParserFactory.createIntegerRangeParser();
        this.timestampParser = inputParserFactory.createTimestampRangeParser();
        this.pathParser = inputParserFactory.createPathParser();
    }

    public InputParameters create(Properties properties) {
        log.info("Input Parameters creation started.", properties);
        InputParametersBuilder inputParametersBuilder = new InputParametersBuilder();

        setCustomerIds(inputParametersBuilder, properties.getCustomerIds());
        setDateRange(inputParametersBuilder, properties.getDateRange());
        setItemsFile(inputParametersBuilder, properties.getItemsFile());
        setItemsCount(inputParametersBuilder, properties.getItemsCount());
        setItemsQuantity(inputParametersBuilder, properties.getItemsQuantity());
        setEventsCount(inputParametersBuilder, properties.getEventsCount());
        setOutDir(inputParametersBuilder, properties.getOutDir());
        setOutFormat(inputParametersBuilder, properties.getFormat());

        return inputParametersBuilder.createInputParameters();
    }

    private void setCustomerIds(InputParametersBuilder inputParametersBuilder, String customerIds) {
        try {
            Range<Integer> integerRange = integerRangeParser.parse(getParamValue(customerIds));
            inputParametersBuilder.setCustomerIds(integerRange);
        } catch (ParseException e) {
            log.warn("Exception during parsing Customer Ids. ", e);
        }
    }

    private void setDateRange(InputParametersBuilder inputParametersBuilder, String dateRange) {
        try {
            Range<ZonedDateTime> timestampRange = timestampParser.parse(getParamValue(dateRange));
            inputParametersBuilder.setDateRange(timestampRange);
        } catch (ParseException e) {
            log.warn("Exception during parsing Date Range: ", e);
        }
    }

    private void setItemsFile(InputParametersBuilder inputParametersBuilder, String itemsFile) {
        try {
            Path path = pathParser.parseFile(getParamValue(itemsFile));
            inputParametersBuilder.setItemsFile(path);
        } catch (ParseException e) {
            log.error("Exception during parsing Items File: ", e);
            throw new ParseException("Exception during parsing Items File: ", e);
        }
    }

    private void setItemsCount(InputParametersBuilder inputParametersBuilder, String itemsCount) {
        try {
            Range<Integer> integerRange = integerRangeParser.parse(getParamValue(itemsCount));
            inputParametersBuilder.setItemsCount(integerRange);
        } catch (ParseException e) {
            log.warn("Exception during parsing Items Count: ", e);
        }
    }

    private void setItemsQuantity(InputParametersBuilder inputParametersBuilder, String itemsQuantity) {
        try {
            Range<Integer> integerRange = integerRangeParser.parse(getParamValue(itemsQuantity));
            inputParametersBuilder.setItemsQuantity(integerRange);
        } catch (ParseException e) {
            log.warn("Exception during parsing Items Quantity: ", e);
        }
    }

    private void setEventsCount(InputParametersBuilder inputParametersBuilder, String eventsCount) {
        try {
            int intValue = integerParser.parse(getParamValue(eventsCount));
            inputParametersBuilder.setEventsCount(intValue);
        } catch (ParseException e) {
            log.warn("Exception during parsing Event Count: ", e);
        }
    }

    private void setOutDir(InputParametersBuilder inputParametersBuilder, String outDir) {
        try {
            Path path = pathParser.parseDirectory(getParamValue(outDir));
            inputParametersBuilder.setOutDir(path);
        } catch (ParseException e) {
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
        return Optional.ofNullable(parameter).orElseThrow(() -> new ParseException("Empty parameter"));
    }
}