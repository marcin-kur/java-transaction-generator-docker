package classes.file_factories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileReaderFactory {
    public Stream<String> getFileLines(Path fileName) throws IOException {
        return Files.lines(fileName);
    }
}
