package classes.properties;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class PropertiesReaderTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldParseCommandLineArgs() {
        // given
        String[] args = {"-customerIds", "1:20",
                "-dateRange", "2018-03-08T00:00:00.000-0100\":\"2018-03-08T23:59:59.999-0100",
                "-itemsFile", "items.csv",
                "-itemsCount", "5:15",
                "-itemsQuantity", "1:30",
                "-eventsCount", "1000",
                "-outDir", "./output"};
        PropertiesReader propertiesReader = new PropertiesReader();

        //when
        Properties properties = propertiesReader.readProperties();

        //then
        assertEquals("1:20", properties.getCustomerIds());
        assertEquals("2018-03-08T00:00:00.000-0100\":\"2018-03-08T23:59:59.999-0100", properties.getDateRange());
        assertEquals("items.csv", properties.getItemsFile());
        assertEquals("5:15", properties.getItemsCount());
        assertEquals("1:30", properties.getItemsQuantity());
        assertEquals("1000", properties.getEventsCount());
        assertEquals("./output", properties.getOutDir());
    }
}
