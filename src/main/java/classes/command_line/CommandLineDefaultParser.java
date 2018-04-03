package classes.command_line;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.util.Arrays;

@Slf4j
public class CommandLineDefaultParser {

    private static final String CUSTOMER_IDS = "customerIds";
    private static final String DATE_RANGE = "dateRange";
    private static final String ITEMS_FILE = "itemsFile";
    private static final String ITEMS_COUNT = "itemsCount";
    private static final String ITEMS_QUANTITY = "itemsQuantity";
    private static final String EVENTS_COUNT = "eventsCount";
    private static final String OUT_DIR = "outDir";
    private static final String OUT_FORMAT = "format";

    private final Options options;
    private final String[] args;

    public CommandLineDefaultParser(String[] args) {
        this.args = args;
        this.options = createOptions();
    }

    private Options createOptions() {
        Options options = new Options();
        options.addOption(CUSTOMER_IDS, true, "Customer Ids");
        options.addOption(DATE_RANGE, true, "Date Range");
        options.addOption(ITEMS_FILE, true, "Items File");
        options.addOption(ITEMS_COUNT, true, "Items Count");
        options.addOption(ITEMS_QUANTITY, true, "Items Quantity");
        options.addOption(EVENTS_COUNT, true, "Events Count");
        options.addOption(OUT_DIR, true, "Out Directory");
        options.addOption(OUT_FORMAT, true, "Output Format");
        return options;
    }

    public CommandLineArgs parse() {
        log.info("Command Line arguments parsing started.", Arrays.asList(args));
        try {
            CommandLineParser parser = new BasicParser();
            CommandLine line = parser.parse(options, args);
            return new CommandLineArgs(
                    line.getOptionValue(CUSTOMER_IDS),
                    line.getOptionValue(DATE_RANGE),
                    line.getOptionValue(ITEMS_FILE),
                    line.getOptionValue(ITEMS_COUNT),
                    line.getOptionValue(ITEMS_QUANTITY),
                    line.getOptionValue(EVENTS_COUNT),
                    line.getOptionValue(OUT_DIR),
                    line.getOptionValue(OUT_FORMAT)
            );
        } catch (ParseException e) {
            log.error("Exception occurred during parsing command line args.", Arrays.asList(args));
            throw new CommandLineParseException("Exception occurred during parsing command line args. ", e);
        }
    }
}