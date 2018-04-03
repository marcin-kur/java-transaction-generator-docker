package classes.command_line;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommandLineArgs {

    private final String customerIds;
    private final String dateRange;
    private final String itemsFile;
    private final String itemsCount;
    private final String itemsQuantity;
    private final String eventsCount;
    private final String outDir;
    private final String format;

    public CommandLineArgs(String customerIds, String dateRange, String itemsFile, String itemsCount, String itemsQuantity, String eventsCount, String outDir, String format) {
        this.customerIds = customerIds;
        this.dateRange = dateRange;
        this.itemsFile = itemsFile;
        this.itemsCount = itemsCount;
        this.itemsQuantity = itemsQuantity;
        this.eventsCount = eventsCount;
        this.outDir = outDir;
        this.format = format;
    }
}
