package classes.input_parameters;

import classes.generators.Range;
import lombok.Setter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Setter
public class InputParametersBuilder {

    private Range<Integer> customerIds = new Range<>(1, 20);
    private Range<ZonedDateTime> dateRange = defaultDateRange();
    private Path itemsFile;
    private Range<Integer> itemsCount = new Range<>(1, 20);
    private Range<Integer> itemsQuantity = new Range<>(1, 20);
    private int eventsCount = 100;
    private Path outDir = Paths.get("/storage");
    private FileFormat outFormat = FileFormat.JSON;

    public InputParameters createInputParameters() {
        return new InputParameters(
                customerIds,
                dateRange,
                itemsFile,
                itemsCount,
                itemsQuantity,
                eventsCount,
                outDir,
                outFormat
        );
    }

    private Range<ZonedDateTime> defaultDateRange() {
        return new Range<>(
                ZonedDateTime.now().with(LocalTime.MIN),
                ZonedDateTime.now().with(LocalTime.MAX)
        );
    }
}