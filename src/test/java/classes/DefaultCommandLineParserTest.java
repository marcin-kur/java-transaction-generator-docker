package classes;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultCommandLineParserTest {

    @Test
    public void shouldParseCommandLineArgs() {
        // given
        DefaultCommandLineParser defaultCommandLineParser = new DefaultCommandLineParser();
        String[] args = {"-customerIds", "1:20",
                "-dateRange", "2018-03-08T00:00:00.000-0100\":\"2018-03-08T23:59:59.999-0100",
                "-itemsFile", "items.csv",
                "-itemsCount", "5:15",
                "-itemsQuantity", "1:30",
                "-eventsCount", "1000",
                "-outDir", "./output"};

        try {
            //when
            CommandLineArgs commandLineArgs = defaultCommandLineParser.parse(args);

            //then
            assertEquals("1:20", commandLineArgs.getCustomerIds());
            assertEquals("2018-03-08T00:00:00.000-0100\":\"2018-03-08T23:59:59.999-0100", commandLineArgs.getDateRange());
            assertEquals("items.csv", commandLineArgs.getItemsFile());
            assertEquals("5:15", commandLineArgs.getItemsCount());
            assertEquals("1:30", commandLineArgs.getItemsQuantity());
            assertEquals("1000", commandLineArgs.getEventsCount());
            assertEquals("./output", commandLineArgs.getOutDir());
        } catch (ParseException e) {
            assertTrue("ParseException shouldn't be thrown:" + e.getMessage(), false);
        }
    }
}
