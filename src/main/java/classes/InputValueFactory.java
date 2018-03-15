package classes;

import java.nio.file.Path;

public class InputValueFactory {

    private final InputIntegerParser integerParser;
    private final InputIntegerRangeParser integerRangeParser;
    private final InputTimestampRangeParser timestampParser;
    private final InputPathParser pathParser;

    public InputValueFactory(InputIntegerParser integerParser, InputIntegerRangeParser integerRangeParser, InputTimestampRangeParser timestampParser, InputPathParser pathParser) {
        this.integerParser = integerParser;
        this.integerRangeParser = integerRangeParser;
        this.timestampParser = timestampParser;
        this.pathParser = pathParser;
    }

    public int getInteger(String valueToParse) {
        validateNotNull(valueToParse);
        return integerParser.parse(valueToParse);
    }

    public IntegerRange getIntegerRange(String valueToParse) {
        validateNotNull(valueToParse);
        return integerRangeParser.parse(valueToParse);
    }

    public TimestampRange getTimestampRange(String valueToParse) {
        validateNotNull(valueToParse);
        return timestampParser.parse(valueToParse);
    }

    public Path getDirectory(String valueToParse) {
        validateNotNull(valueToParse);
        return pathParser.parseDirectory(valueToParse);
    }

    public Path getMandatoryFile(String valueToParse) {
        validateNotNull(valueToParse);
        return pathParser.parseFile(valueToParse);
    }

    private void validateNotNull(String valueToParse) {
        if (valueToParse == null) {
            throw new InputParseException("Value to parse is null");
        }
    }
}