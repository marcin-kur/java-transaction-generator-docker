package classes.input_parameters;

import classes.parsers.IntegerParser;
import classes.parsers.IntegerRangeParser;
import classes.parsers.TimestampRangeParser;

public class InputParserFactory {

    public IntegerParser createIntegerParser() {
        return new IntegerParser();
    }

    public IntegerRangeParser createIntegerRangeParser() {
        return new IntegerRangeParser();
    }

    public InputPathParser createPathParser() {
        return new InputPathParser();
    }

    public TimestampRangeParser createTimestampRangeParser() {
        return new TimestampRangeParser();
    }
}
