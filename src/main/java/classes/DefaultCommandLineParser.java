package classes;

import org.apache.commons.cli.*;

import java.util.Arrays;

public class DefaultCommandLineParser {

    private static final String CUSTOMER_IDS = "customerIds";
    private static final String DATE_RANGE = "dateRange";
    private static final String ITEMS_FILE = "itemsFile";
    private static final String ITEMS_COUNT = "itemsCount";
    private static final String ITEMS_QUANTITY = "itemsQuantity";
    private static final String EVENTS_COUNT = "eventsCount";
    private static final String OUT_DIR = "outDir";

    private final Options options;

    public DefaultCommandLineParser() {
        options = createOptions();
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
        return options;
    }

    public CommandLineArgs parse(String[] args) throws ParseException {
        CommandLineParser parser = new BasicParser();
        CommandLine line = parser.parse(options, args);
        return new CommandLineArgs(
                line.getOptionValue(CUSTOMER_IDS),
                line.getOptionValue(DATE_RANGE),
                line.getOptionValue(ITEMS_FILE),
                line.getOptionValue(ITEMS_COUNT),
                line.getOptionValue(ITEMS_QUANTITY),
                line.getOptionValue(EVENTS_COUNT),
                line.getOptionValue(OUT_DIR)
        );
    }
}