package classes.input_parameters;

public interface InputParserFactory {

    InputIntegerParser createIntegerParser();

    InputIntegerRangeParser createIntegerRangeParser();

    InputPathParser createPathParser();

    InputTimestampRangeParser createTimestampRangeParser();
}