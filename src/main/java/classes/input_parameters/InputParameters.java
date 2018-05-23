package classes.input_parameters;

import classes.generators.Range;
import lombok.Getter;
import lombok.ToString;

import java.nio.file.Path;
import java.time.ZonedDateTime;

@ToString
@Getter
public class InputParameters {

    private final Range<Integer> customerIds;
    private final Range<ZonedDateTime> dateRange;
    private final Path itemsFile;
    private final Range<Integer> itemsCount;
    private final Range<Integer> itemsQuantity;
    private final int eventsCount;
    private final Path outDir;
    private final FileFormat outFormat;

    public InputParameters(
            Range<Integer> customerIds,
            Range<ZonedDateTime> dateRange,
            Path itemsFile,
            Range<Integer> itemsCount,
            Range<Integer> itemsQuantity,
            int eventsCount,
            Path outDir,
            FileFormat outFormat) {
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
