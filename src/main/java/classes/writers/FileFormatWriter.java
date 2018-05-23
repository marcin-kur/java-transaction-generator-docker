package classes.writers;

import classes.model.Transaction;
import classes.input_parameters.FileFormat;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class FileFormatWriter {

    private final FileFormat fileFormat;

    public FileFormatWriter(FileFormat fileFormat) {
        this.fileFormat = fileFormat;
    }

    public final String getFileExtension() {
        return fileFormat.getFileExtension();
    }

    public abstract String getTransaction(Transaction transaction) throws JsonProcessingException;

    public static FileFormatWriter getFileFormatWriter(FileFormat fileFormat) {
        switch (fileFormat) {
            case JSON:
                return new FileFormatJsonWriter(fileFormat);
            case XML:
                return new FileFormatXmlWriter(fileFormat);
            case YAML:
                return new FileFormatYamlWriter(fileFormat);
            default:
                return new FileFormatJsonWriter(fileFormat);
        }
    }
}
