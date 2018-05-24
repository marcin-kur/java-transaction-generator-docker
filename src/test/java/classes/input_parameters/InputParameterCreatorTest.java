package classes.input_parameters;

import classes.TestUtils;
import classes.properties.AppProperties;
import classes.parsers.ParseException;
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
        AppProperties appProperties = new AppProperties(
                "1:5",
                "2018-03-01T00:00:00.000-0100:2018-03-01T23:59:59.999-0100",
                "items.csv",
                "5:10",
                "1:5",
                "10",
                "./output",
                "XML");

        InputPathParser pathParser = mock(InputPathParser.class);
        when(pathParser.validateFile(any(Path.class))).thenReturn(true);
        when(pathParser.validateDirectory(any(Path.class))).thenReturn(true);
        when(pathParser.parseFile(any(String.class))).thenCallRealMethod();
        when(pathParser.parseDirectory(any(String.class))).thenCallRealMethod();

        InputParserFactory inputParserFactory = mock(InputParserFactory.class);
        when(inputParserFactory.createIntegerParser()).thenCallRealMethod();
        when(inputParserFactory.createIntegerRangeParser()).thenCallRealMethod();
        when(inputParserFactory.createTimestampRangeParser()).thenCallRealMethod();
        when(inputParserFactory.createPathParser()).thenReturn(pathParser);

        InputParametersCreator inputParametersCreator = new InputParametersCreator(inputParserFactory);

        try {
            //when
            InputParameters inputParameters = inputParametersCreator.create(appProperties);

            //then
            assertEquals((int) inputParameters.getCustomerIds().getLowerLimit(), 1);
            assertEquals((int) inputParameters.getCustomerIds().getUpperLimit(), 5);

            ZonedDateTime beginOfDay = ZonedDateTime.of(2018, 3, 1, 0, 0, 0, 0, ZoneId.of("-01:00"));
            assertEquals(inputParameters.getDateRange().getLowerLimit(), beginOfDay);
            assertEquals(inputParameters.getDateRange().getUpperLimit(), beginOfDay.plusDays(1).minus(1, ChronoUnit.MILLIS));

            assertEquals(inputParameters.getItemsFile(), Paths.get("items.csv"));

            assertEquals((int) inputParameters.getItemsCount().getLowerLimit(), 5);
            assertEquals((int) inputParameters.getItemsCount().getUpperLimit(), 10);

            assertEquals((int) inputParameters.getItemsQuantity().getLowerLimit(), 1);
            assertEquals((int) inputParameters.getItemsQuantity().getUpperLimit(), 5);

            assertEquals((int) inputParameters.getEventsCount(), 10);

            assertEquals(inputParameters.getOutDir(), Paths.get("./output"));

            assertEquals(inputParameters.getOutFormat(), FileFormat.XML);
        } catch (ParseException e) {
            assertTrue("ParseException shouldn't be thrown:" + e.getMessage(), false);
        }
    }

    @Test
    public void shouldCreateDefaultInputParametersFromEmptyCommandLineArgs() {
        // given
        AppProperties appProperties = new AppProperties(
                null,
                null,
                "items.csv",
                null,
                null,
                null,
                null,
                null);

        InputPathParser pathParser = mock(InputPathParser.class);
        when(pathParser.validateFile(any(Path.class))).thenReturn(true);
        when(pathParser.validateDirectory(any(Path.class))).thenReturn(true);
        when(pathParser.parseFile(any(String.class))).thenCallRealMethod();
        when(pathParser.parseDirectory(any(String.class))).thenCallRealMethod();

        InputParserFactory inputParserFactory = mock(InputParserFactory.class);
        when(inputParserFactory.createIntegerParser()).thenCallRealMethod();
        when(inputParserFactory.createIntegerRangeParser()).thenCallRealMethod();
        when(inputParserFactory.createTimestampRangeParser()).thenCallRealMethod();
        when(inputParserFactory.createPathParser()).thenReturn(pathParser);

        InputParametersCreator inputParametersCreator = new InputParametersCreator(inputParserFactory);

        try {
            //when
            InputParameters inputParameters = inputParametersCreator.create(appProperties);

            //then
            verifyDefaultValues(inputParameters);
        } catch (ParseException e) {
            assertTrue("ParseException shouldn't be thrown:" + e.getMessage(), false);
        }
    }

    @Test
    public void shouldCreateDefaultInputParametersFromInvalidCommandLineArgs() {
        // given
        AppProperties appProperties = new AppProperties(
                "a:a",
                "aaaa-03-01T00:00:00.000-0100:2018-03-01T23:59:59.999-0100",
                "items.csv",
                "a:b",
                "a:b",
                "a",
                "./abcd",
                "ABC");

        InputPathParser pathParser = mock(InputPathParser.class);
        when(pathParser.validateFile(any(Path.class))).thenReturn(true);
        when(pathParser.validateDirectory(any(Path.class))).thenReturn(false);
        when(pathParser.parseFile(any(String.class))).thenCallRealMethod();
        when(pathParser.parseDirectory(any(String.class))).thenCallRealMethod();

        InputParserFactory inputParserFactory = mock(InputParserFactory.class);
        when(inputParserFactory.createIntegerParser()).thenCallRealMethod();
        when(inputParserFactory.createIntegerRangeParser()).thenCallRealMethod();
        when(inputParserFactory.createTimestampRangeParser()).thenCallRealMethod();
        when(inputParserFactory.createPathParser()).thenReturn(pathParser);

        InputParametersCreator inputParametersCreator = new InputParametersCreator(inputParserFactory);

        try {
            //when
            InputParameters inputParameters = inputParametersCreator.create(appProperties);

            //then
            verifyDefaultValues(inputParameters);
        } catch (ParseException e) {
            assertTrue("ParseException shouldn't be thrown:" + e.getMessage(), false);
        }
    }

    @Test
    public void shouldThrowExceptionWhenItemsFileIsEmpty() {
        // given
        AppProperties appProperties = new AppProperties(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        InputPathParser pathParser = mock(InputPathParser.class);
        when(pathParser.validateFile(any(Path.class))).thenReturn(true);
        when(pathParser.validateDirectory(any(Path.class))).thenReturn(true);
        when(pathParser.parseFile(any(String.class))).thenCallRealMethod();
        when(pathParser.parseDirectory(any(String.class))).thenCallRealMethod();

        InputParserFactory inputParserFactory = mock(InputParserFactory.class);
        when(inputParserFactory.createIntegerParser()).thenCallRealMethod();
        when(inputParserFactory.createIntegerRangeParser()).thenCallRealMethod();
        when(inputParserFactory.createTimestampRangeParser()).thenCallRealMethod();
        when(inputParserFactory.createPathParser()).thenReturn(pathParser);

        InputParametersCreator inputParametersCreator = new InputParametersCreator(inputParserFactory);

        try {
            //when
            InputParameters inputParameters = inputParametersCreator.create(appProperties);

            //then
            assertTrue("ParseException should be thrown", false);
        } catch (ParseException e) {
            assertTrue("ParseException should be thrown:" + e.getMessage(), true);
        }
    }

    private void verifyDefaultValues(InputParameters inputParameters) {
        assertEquals((int) inputParameters.getCustomerIds().getLowerLimit(), 1);
        assertEquals((int) inputParameters.getCustomerIds().getUpperLimit(), 20);

        assertEquals(inputParameters.getDateRange().getLowerLimit(), testUtils.getBeginOfToday());
        assertEquals(inputParameters.getDateRange().getUpperLimit(), testUtils.getEndOfToday());

        assertEquals(inputParameters.getItemsFile(), Paths.get("items.csv"));

        assertEquals((int) inputParameters.getItemsCount().getLowerLimit(), 1);
        assertEquals((int) inputParameters.getItemsCount().getUpperLimit(), 20);

        assertEquals((int) inputParameters.getItemsQuantity().getLowerLimit(), 1);
        assertEquals((int) inputParameters.getItemsQuantity().getUpperLimit(), 20);

        assertEquals(inputParameters.getEventsCount(), 100);

        assertEquals(inputParameters.getOutDir(), Paths.get("/storage"));

        assertEquals(inputParameters.getOutFormat(), FileFormat.JSON);

    }
}
