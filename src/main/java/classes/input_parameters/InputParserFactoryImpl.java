package classes.input_parameters;

public class InputParserFactoryImpl implements InputParserFactory {

    public InputIntegerParser createIntegerParser() {
        return new InputIntegerParser();
    }

    public InputIntegerRangeParser createIntegerRangeParser() {
        return new InputIntegerRangeParser();
    }

    public InputPathParser createPathParser() {
        return new InputPathParser();
    }

    public InputTimestampRangeParser createTimestampRangeParser() {
        return new InputTimestampRangeParser();
    }
}
