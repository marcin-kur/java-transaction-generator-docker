package classes.writers;

import classes.model.Transaction;
import classes.input_parameters.FileFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.ZonedDateTime;

public class FileFormatXmlWriter extends FileFormatWriter {

    private final XmlMapper xmlMapper;

    public FileFormatXmlWriter(FileFormat fileFormat) {
        super(fileFormat);
        xmlMapper = new XmlMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        xmlMapper.registerModule(javaTimeModule);
    }

    @Override
    public String getTransaction(Transaction transaction) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(transaction);
    }
}
