package classes.input_parameters;

import classes.generators.IntegerRange;
import classes.generators.TimestampRange;

import java.nio.file.Path;

public class InputParameters {

    private final IntegerRange customerIds;
    private final TimestampRange dateRange;
    private final Path itemsFile;
    private final IntegerRange itemsCount;
    private final IntegerRange itemsQuantity;
    private final int eventsCount;
    private final Path outDir;

    public InputParameters(IntegerRange customerIds, TimestampRange dateRange, Path itemsFile, IntegerRange itemsCount, IntegerRange itemsQuantity, int eventsCount, Path outDir) {
        this.customerIds = customerIds;
        this.dateRange = dateRange;
        this.itemsFile = itemsFile;
        this.itemsCount = itemsCount;
        this.itemsQuantity = itemsQuantity;
        this.eventsCount = eventsCount;
        this.outDir = outDir;
    }

    public IntegerRange getCustomerIds() {
        return customerIds;
    }

    public TimestampRange getDateRange() {
        return dateRange;
    }

    public Path getItemsFile() {
        return itemsFile;
    }

    public IntegerRange getItemsCount() {
        return itemsCount;
    }

    public IntegerRange getItemsQuantity() {
        return itemsQuantity;
    }

    public int getEventsCount() {
        return eventsCount;
    }

    public Path getOutDir() {
        return outDir;
    }
}
