package classes.input_parameters;

import classes.parsers.ParseException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputPathParser {
    public Path parseFile(String valueToParse) {
        Path path = Paths.get(valueToParse);
        if (!validateFile(path)) {
            throw new ParseException("File " + valueToParse + " not exists or isn't readable");
        }
        return path;
    }

    public Path parseDirectory(String valueToParse) {
        Path path = Paths.get(valueToParse);
        if (!validateDirectory(path)) {
            throw new ParseException("Directory " + valueToParse + " not exists");
        }
        return path;
    }

    public boolean validateFile(Path path) {
        return Files.exists(path) && Files.isReadable(path);
    }

    public boolean validateDirectory(Path path) {
        return Files.isDirectory(path);
    }
}
