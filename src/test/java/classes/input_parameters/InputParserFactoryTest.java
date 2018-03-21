package classes.input_parameters;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class InputParserFactoryTest {

    @Test
    public void shouldReturnCorrectObjects() {
        // given
        InputParserFactory inputParserFactory = new InputParserFactoryImpl();

        // when
        Object integerParser = inputParserFactory.createIntegerParser();
        Object integerRangeParser = inputParserFactory.createIntegerRangeParser();
        Object pathParser = inputParserFactory.createPathParser();
        Object timestampRangeParser = inputParserFactory.createTimestampRangeParser();

        // then
        assertTrue(integerParser instanceof InputIntegerParser);
        assertTrue(integerRangeParser instanceof InputIntegerRangeParser);
        assertTrue(pathParser instanceof InputPathParser);
        assertTrue(timestampRangeParser instanceof InputTimestampRangeParser);
    }
}