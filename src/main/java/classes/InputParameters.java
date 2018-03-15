package classes;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class InputParameters {
    private IntegerRange customerIds = new IntegerRange(1, 20);
    private TimestampRange dateRange = defaultDateRange();
    private Path itemsFile;
    private IntegerRange itemsCount = new IntegerRange(1, 20);
    private IntegerRange itemsQuantity = new IntegerRange(1, 20);
    private int eventsCount = 100;
    private Path outDir = Paths.get("");

    public InputParameters(Path itemsFile) {
        this.itemsFile = itemsFile;
    }

    private TimestampRange defaultDateRange() {
        ZonedDateTime beginOfToday = ZonedDateTime.of(LocalDate.now(ZoneId.systemDefault()), LocalTime.MIDNIGHT, ZoneId.systemDefault());
        ZonedDateTime endOfToday = beginOfToday.plusDays(1).minus(1, ChronoUnit.MILLIS);
        return new TimestampRange(beginOfToday, endOfToday);
    }

    public IntegerRange getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(IntegerRange customerIds) {
        this.customerIds = customerIds;
    }

    public TimestampRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(TimestampRange dateRange) {
        this.dateRange = dateRange;
    }

    public Path getItemsFile() {
        return itemsFile;
    }

    public IntegerRange getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(IntegerRange itemsCoun) {
        this.itemsCount = itemsCoun;
    }

    public IntegerRange getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(IntegerRange itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public int getEventsCount() {
        return eventsCount;
    }

    public void setEventsCount(int eventsCount) {
        this.eventsCount = eventsCount;
    }

    public Path getOutDir() {
        return outDir;
    }

    public void setOutDir(Path outDir) {
        this.outDir = outDir;
    }
}
