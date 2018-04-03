package classes.input_parameters;

import classes.generators.IntegerRange;
import classes.generators.TimestampRange;
import lombok.Setter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Setter
public class InputParametersBuilder {

    private IntegerRange customerIds = new IntegerRange(1, 20);
    private TimestampRange dateRange = defaultDateRange();
    private Path itemsFile;
    private IntegerRange itemsCount = new IntegerRange(1, 20);
    private IntegerRange itemsQuantity = new IntegerRange(1, 20);
    private int eventsCount = 100;
    private Path outDir = Paths.get("");
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

    private TimestampRange defaultDateRange() {
        return new TimestampRange(
                ZonedDateTime.now().with(LocalTime.MIN),
                ZonedDateTime.now().with(LocalTime.MAX)
        );
    }
}