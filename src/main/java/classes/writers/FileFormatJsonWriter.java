package classes.writers;

import classes.generators.Transaction;
import classes.input_parameters.FileFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.ZonedDateTime;

public class FileFormatJsonWriter extends FileFormatWriter {

    private final ObjectMapper objectMapper;

    public FileFormatJsonWriter(FileFormat fileFormat) {
        super(fileFormat);
        objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        objectMapper.registerModule(javaTimeModule);
    }

    @Override
    public String getTransaction(Transaction transaction) throws JsonProcessingException {
        return objectMapper.writeValueAsString(transaction);
    }
}
