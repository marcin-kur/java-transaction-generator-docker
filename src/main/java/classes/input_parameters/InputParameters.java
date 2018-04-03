package classes.input_parameters;

import classes.generators.IntegerRange;
import classes.generators.TimestampRange;
import lombok.Getter;
import lombok.ToString;

import java.nio.file.Path;

@ToString
@Getter
public class InputParameters {

    private final IntegerRange customerIds;
    private final TimestampRange dateRange;
    private final Path itemsFile;
    private final IntegerRange itemsCount;
    private final IntegerRange itemsQuantity;
    private final int eventsCount;
    private final Path outDir;
    private final FileFormat outFormat;

    public InputParameters(IntegerRange customerIds, TimestampRange dateRange, Path itemsFile, IntegerRange itemsCount, IntegerRange itemsQuantity, int eventsCount, Path outDir, FileFormat outFormat) {
        this.customerIds = customerIds;
        this.dateRange = dateRange;
        this.itemsFile = itemsFile;
        this.itemsCount = itemsCount;
        this.itemsQuantity = itemsQuantity;
        this.eventsCount = eventsCount;
        this.outDir = outDir;
        this.outFormat = outFormat;
    }
}
