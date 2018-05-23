package classes.writers;

import classes.model.Item;
import classes.model.Transaction;
import classes.input_parameters.FileFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileFormatWriterTest {

    @Test
    public void shouldSuccessfullySaveJSON() {
        // given
        FileFormatWriter fileFormatWriter = FileFormatWriter.getFileFormatWriter(FileFormat.JSON);
        Transaction transaction = new Transaction(1, 1, ZonedDateTime.now(),
                Arrays.asList(new Item("p1", 1, BigDecimal.valueOf(1))));

        try {
            // when
            String transactionString = fileFormatWriter.getTransaction(transaction);

            // then
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
            objectMapper.readTree(transactionString);
            assertTrue(true);
        } catch (JsonProcessingException e) {
            assertFalse("JsonProcessingException shouldn't be thrown", true);
        } catch (IOException e) {
            assertFalse("IOException shouldn't be thrown", true);
        }
    }

    @Test
    public void shouldSuccessfullySaveXml() {
        // given
        FileFormatWriter fileFormatWriter = FileFormatWriter.getFileFormatWriter(FileFormat.XML);
        Transaction transaction = new Transaction(1, 1, ZonedDateTime.now(),
                Arrays.asList(new Item("p1", 1, BigDecimal.valueOf(1))));

        try {
            // when
            String transactionString = fileFormatWriter.getTransaction(transaction);

            // then
            XmlMapper objectMapper = new XmlMapper();
            objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
            objectMapper.readTree(transactionString);
            assertTrue(true);
        } catch (JsonProcessingException e) {
            assertFalse("JsonProcessingException shouldn't be thrown", true);
        } catch (IOException e) {
            assertFalse("IOException shouldn't be thrown", true);
        }
    }

    @Test
    public void shouldSuccessfullySaveYaml() {
        // given
        FileFormatWriter fileFormatWriter = FileFormatWriter.getFileFormatWriter(FileFormat.YAML);
        Transaction transaction = new Transaction(1, 1, ZonedDateTime.now(),
                Arrays.asList(new Item("p1", 1, BigDecimal.valueOf(1))));

        try {
            // when
            String transactionString = fileFormatWriter.getTransaction(transaction);

            // then
            YAMLMapper objectMapper = new YAMLMapper();
            objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
            objectMapper.readTree(transactionString);
            assertTrue(true);
        } catch (JsonProcessingException e) {
            assertFalse("JsonProcessingException shouldn't be thrown", true);
        } catch (IOException e) {
            assertFalse("IOException shouldn't be thrown", true);
        }
    }
}
