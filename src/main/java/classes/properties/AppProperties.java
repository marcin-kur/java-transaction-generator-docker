package classes.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class AppProperties {
    private final String customerIds;
    private final String dateRange;
    private final String itemsFile;
    private final String itemsCount;
    private final String itemsQuantity;
    private final String eventsCount;
    private final String outDir;
    private final String format;
}