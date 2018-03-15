package classes;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputParameterCreatorTest {

    private final TestUtils testUtils = new TestUtils();

    @Test
    public void shouldCreateInputParametersFromCommandLineArgs() {
        // given
        CommandLineArgs commandLineArgs = new CommandLineArgs(
                "1:5",
                "2018-03-01T00:00:00.000-0100:2018-03-01T23:59:59.999-0100",
                "items.csv",
                "5:10",
                "1:5",
                "10",
                "./output");
        InputIntegerParser integerParser = new InputIntegerParser();
        InputIntegerRangeParser integerRangeParser = new InputIntegerRangeParser();
        InputTimestampRangeParser timestampParser = new InputTimestampRangeParser();
        InputPathParser pathParser = mock(InputPathParser.class);
        when(pathParser.validateFile(any(Path.class))).thenReturn(true);
        when(pathParser.validateDirectory(any(Path.class))).thenReturn(true);
        when(pathParser.parseFile(any(String.class))).thenCallRealMethod();
        when(pathParser.parseDirectory(any(String.class))).thenCallRealMethod();
        InputValueFactory inputValueFactory = new InputValueFactory(integerParser, integerRangeParser, timestampParser, pathParser);
        InputParametersCreator inputParametersCreator = new InputParametersCreator(inputValueFactory);

        try {
            //when
            InputParameters inputParameters = inputParametersCreator.create(commandLineArgs);

            //then
            assertEquals(inputParameters.getCustomerIds().getLowerLimit(), 1);
            assertEquals(inputParameters.getCustomerIds().getUpperLimit(), 5);

            ZonedDateTime beginOfDay = ZonedDateTime.of(2018, 3, 1, 0, 0, 0, 0, ZoneId.of("-01:00"));
            assertEquals(inputParameters.getDateRange().getLowerLimit(), beginOfDay);
            assertEquals(inputParameters.getDateRange().getUpperLimit(), beginOfDay.plusDays(1).minus(1, ChronoUnit.MILLIS));

            assertEquals(inputParameters.getItemsFile(), Paths.get("items.csv"));

            assertEquals(inputParameters.getItemsCount().getLowerLimit(), 5);
            assertEquals(inputParameters.getItemsCount().getUpperLimit(), 10);

            assertEquals(inputParameters.getItemsQuantity().getLowerLimit(), 1);
            assertEquals(inputParameters.getItemsQuantity().getUpperLimit(), 5);

            assertEquals(inputParameters.getEventsCount(), 10);

            assertEquals(inputParameters.getOutDir(), Paths.get("./output"));
        } catch (InputParseException e) {
            assertTrue("InputParseException shouldn't be thrown:" + e.getMessage(), false);
        }
    }

    @Test
    public void shouldCreateDefaultInputParametersFromEmptyCommandLineArgs() {
        // given
        CommandLineArgs commandLineArgs = new CommandLineArgs(
                null,
                null,
                "items.csv",
                null,
                null,
                null,
                null);
        InputIntegerParser integerParser = new InputIntegerParser();
        InputIntegerRangeParser integerRangeParser = new InputIntegerRangeParser();
        InputTimestampRangeParser timestampParser = new InputTimestampRangeParser();
        InputPathParser pathParser = mock(InputPathParser.class);
        when(pathParser.validateFile(any(Path.class))).thenReturn(true);
        when(pathParser.validateDirectory(any(Path.class))).thenReturn(true);
        when(pathParser.parseFile(any(String.class))).thenCallRealMethod();
        when(pathParser.parseDirectory(any(String.class))).thenCallRealMethod();
        InputValueFactory inputValueFactory = new InputValueFactory(integerParser, integerRangeParser, timestampParser, pathParser);
        InputParametersCreator inputParametersCreator = new InputParametersCreator(inputValueFactory);

        try {
            //when
            InputParameters inputParameters = inputParametersCreator.create(commandLineArgs);

            //then
            verifyDefaultValues(inputParameters);
        } catch (InputParseException e) {
            assertTrue("InputParseException shouldn't be thrown:" + e.getMessage(), false);
        }
    }

    @Test
    public void shouldCreateDefaultInputParametersFromInvalidCommandLineArgs() {
        // given
        CommandLineArgs commandLineArgs = new CommandLineArgs(
                "a:a",
                "aaaa-03-01T00:00:00.000-0100:2018-03-01T23:59:59.999-0100",
                "items.csv",
                "a:b",
                "a:b",
                "a",
                "./abcd");
        InputIntegerParser integerParser = new InputIntegerParser();
        InputIntegerRangeParser integerRangeParser = new InputIntegerRangeParser();
        InputTimestampRangeParser timestampParser = new InputTimestampRangeParser();
        InputPathParser pathParser = mock(InputPathParser.class);
        when(pathParser.validateFile(any(Path.class))).thenReturn(true);
        when(pathParser.validateDirectory(any(Path.class))).thenReturn(false);
        when(pathParser.parseFile(any(String.class))).thenCallRealMethod();
        when(pathParser.parseDirectory(any(String.class))).thenCallRealMethod();
        InputValueFactory inputValueFactory = new InputValueFactory(integerParser, integerRangeParser, timestampParser, pathParser);
        InputParametersCreator inputParametersCreator = new InputParametersCreator(inputValueFactory);

        try {
            //when
            InputParameters inputParameters = inputParametersCreator.create(commandLineArgs);

            //then
            verifyDefaultValues(inputParameters);
        } catch (InputParseException e) {
            assertTrue("InputParseException shouldn't be thrown:" + e.getMessage(), false);
        }
    }

    @Test
    public void shouldThrowExceptionWhenItemsFileIsEmpty() {
        // given
        CommandLineArgs commandLineArgs = new CommandLineArgs(
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        InputIntegerParser integerParser = new InputIntegerParser();
        InputIntegerRangeParser integerRangeParser = new InputIntegerRangeParser();
        InputTimestampRangeParser timestampParser = new InputTimestampRangeParser();
        InputPathParser pathParser = mock(InputPathParser.class);
        when(pathParser.validateFile(any(Path.class))).thenReturn(true);
        when(pathParser.validateDirectory(any(Path.class))).thenReturn(true);
        when(pathParser.parseFile(any(String.class))).thenCallRealMethod();
        when(pathParser.parseDirectory(any(String.class))).thenCallRealMethod();
        InputValueFactory inputValueFactory = new InputValueFactory(integerParser, integerRangeParser, timestampParser, pathParser);
        InputParametersCreator inputParametersCreator = new InputParametersCreator(inputValueFactory);

        try {
            //when
            InputParameters inputParameters = inputParametersCreator.create(commandLineArgs);

            //then
            assertTrue("InputParseException should be thrown", false);
        } catch (InputParseException e) {
            assertTrue("InputParseException should be thrown:" + e.getMessage(), true);
        }
    }

    private void verifyDefaultValues(InputParameters inputParameters) {
        assertEquals(inputParameters.getCustomerIds().getLowerLimit(), 1);
        assertEquals(inputParameters.getCustomerIds().getUpperLimit(), 20);

        assertEquals(inputParameters.getDateRange().getLowerLimit(), testUtils.getBeginOfToday());
        assertEquals(inputParameters.getDateRange().getUpperLimit(), testUtils.getEndOfToday());

        assertEquals(inputParameters.getItemsFile(), Paths.get("items.csv"));

        assertEquals(inputParameters.getItemsCount().getLowerLimit(), 1);
        assertEquals(inputParameters.getItemsCount().getUpperLimit(), 20);

        assertEquals(inputParameters.getItemsQuantity().getLowerLimit(), 1);
        assertEquals(inputParameters.getItemsQuantity().getUpperLimit(), 20);

        assertEquals(inputParameters.getEventsCount(), 100);

        assertEquals(inputParameters.getOutDir(), Paths.get(""));
    }
}
