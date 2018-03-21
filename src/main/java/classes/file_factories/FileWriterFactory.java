package classes.file_factories;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;

public class FileWriterFactory {
    public Writer getFileWriter(Path filePath) throws IOException {
        return new FileWriter(filePath.toString());
    }
}
