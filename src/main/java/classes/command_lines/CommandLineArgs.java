package classes.command_lines;

public class CommandLineArgs {

    private final String customerIds;
    private final String dateRange;
    private final String itemsFile;
    private final String itemsCount;
    private final String itemsQuantity;
    private final String eventsCount;
    private final String outDir;

    public CommandLineArgs(String customerIds, String dateRange, String itemsFile, String itemsCount, String itemsQuantity, String eventsCount, String outDir) {
        this.customerIds = customerIds;
        this.dateRange = dateRange;
        this.itemsFile = itemsFile;
        this.itemsCount = itemsCount;
        this.itemsQuantity = itemsQuantity;
        this.eventsCount = eventsCount;
        this.outDir = outDir;
    }

    public String getCustomerIds() {
        return customerIds;
    }

    public String getDateRange() {
        return dateRange;
    }

    public String getItemsFile() {
        return itemsFile;
    }

    public String getItemsCount() {
        return itemsCount;
    }

    public String getItemsQuantity() {
        return itemsQuantity;
    }

    public String getEventsCount() {
        return eventsCount;
    }

    public String getOutDir() {
        return outDir;
    }

    @Override
    public String toString() {
        return "CommandLineArgs{" +
                "customerIds='" + customerIds + '\'' +
                ", dateRange='" + dateRange + '\'' +
                ", itemsFile='" + itemsFile + '\'' +
                ", itemsCount='" + itemsCount + '\'' +
                ", itemsQuantity='" + itemsQuantity + '\'' +
                ", eventsCount='" + eventsCount + '\'' +
                ", outDir='" + outDir + '\'' +
                '}';
    }
}
