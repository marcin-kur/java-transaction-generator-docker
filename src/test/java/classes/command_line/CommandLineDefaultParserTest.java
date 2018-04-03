package classes.command_line;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CommandLineDefaultParserTest {

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
        CommandLineDefaultParser commandLineDefaultParser = new CommandLineDefaultParser(args);

        //when
        CommandLineArgs commandLineArgs = commandLineDefaultParser.parse();

        //then
        assertEquals("1:20", commandLineArgs.getCustomerIds());
        assertEquals("2018-03-08T00:00:00.000-0100\":\"2018-03-08T23:59:59.999-0100", commandLineArgs.getDateRange());
        assertEquals("items.csv", commandLineArgs.getItemsFile());
        assertEquals("5:15", commandLineArgs.getItemsCount());
        assertEquals("1:30", commandLineArgs.getItemsQuantity());
        assertEquals("1000", commandLineArgs.getEventsCount());
        assertEquals("./output", commandLineArgs.getOutDir());
    }

    @Test
    public void shouldThrowCommandLineParseException() {
        // given
        String[] args = {"-dummmyArg", "dummmyValue"};
        CommandLineDefaultParser commandLineDefaultParser = new CommandLineDefaultParser(args);

        //then
        exception.expect(CommandLineParseException.class);
        commandLineDefaultParser.parse();
    }
}
