package classes.writers;

import classes.generators.Transaction;
import classes.input_parameters.FileFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.ZonedDateTime;

public class FileFormatYamlWriter extends FileFormatWriter {

    private final YAMLMapper yamlMapper;

    public FileFormatYamlWriter(FileFormat fileFormat) {
        super(fileFormat);
        yamlMapper = new YAMLMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        yamlMapper.registerModule(javaTimeModule);
    }

    @Override
    public String getTransaction(Transaction transaction) throws JsonProcessingException {
        return yamlMapper.writeValueAsString(transaction);
    }
}