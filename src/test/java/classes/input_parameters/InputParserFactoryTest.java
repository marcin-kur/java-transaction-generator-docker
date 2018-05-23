package classes.input_parameters;

import classes.parsers.IntegerParser;
import classes.parsers.IntegerRangeParser;
import classes.parsers.TimestampRangeParser;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class InputParserFactoryTest {

    @Test
    public void shouldReturnCorrectObjects() {
        // given
        InputParserFactory inputParserFactory = new InputParserFactory();

        // when
        Object integerParser = inputParserFactory.createIntegerParser();
        Object integerRangeParser = inputParserFactory.createIntegerRangeParser();
        Object pathParser = inputParserFactory.createPathParser();
        Object timestampRangeParser = inputParserFactory.createTimestampRangeParser();

        // then
        assertTrue(integerParser instanceof IntegerParser);
        assertTrue(integerRangeParser instanceof IntegerRangeParser);
        assertTrue(pathParser instanceof InputPathParser);
        assertTrue(timestampRangeParser instanceof TimestampRangeParser);
    }
}