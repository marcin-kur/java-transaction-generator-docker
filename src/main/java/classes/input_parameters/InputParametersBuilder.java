package classes.input_parameters;

import classes.generators.IntegerRange;
import classes.generators.TimestampRange;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class InputParametersBuilder {

    private IntegerRange customerIds = new IntegerRange(1, 20);
    private TimestampRange dateRange = defaultDateRange();
    private Path itemsFile;
    private IntegerRange itemsCount = new IntegerRange(1, 20);
    private IntegerRange itemsQuantity = new IntegerRange(1, 20);
    private int eventsCount = 100;
    private Path outDir = Paths.get("");

    public void setCustomerIds(IntegerRange customerIds) {
        this.customerIds = customerIds;
    }

    public void setDateRange(TimestampRange dateRange) {
        this.dateRange = dateRange;
    }

    public void setItemsFile(Path itemsFile) {
        this.itemsFile = itemsFile;
    }

    public void setItemsCount(IntegerRange itemsCount) {
        this.itemsCount = itemsCount;
    }

    public void setItemsQuantity(IntegerRange itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public void setEventsCount(int eventsCount) {
        this.eventsCount = eventsCount;
    }

    public void setOutDir(Path outDir) {
        this.outDir = outDir;
    }

    public InputParameters createInputParameters() {
        return new InputParameters(
                customerIds,
                dateRange,
                itemsFile,
                itemsCount,
                itemsQuantity,
                eventsCount,
                outDir
        );
    }

    private TimestampRange defaultDateRange() {
        ZonedDateTime beginOfToday = ZonedDateTime.of(LocalDate.now(ZoneId.systemDefault()), LocalTime.MIDNIGHT, ZoneId.systemDefault());
        ZonedDateTime endOfToday = beginOfToday.plusDays(1).minus(1, ChronoUnit.MILLIS);
        return new TimestampRange(beginOfToday, endOfToday);
    }
}
