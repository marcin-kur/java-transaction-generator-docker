package classes.properties;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

@Slf4j
public class PropertiesReader {
    private static final String FILE = "storage/generator.properties";
    private static final String CUSTOMER_IDS = "customerIds";
    private static final String DATE_RANGE = "dateRange";
    private static final String ITEMS_FILE = "itemsFile";
    private static final String ITEMS_COUNT = "itemsCount";
    private static final String ITEMS_QUANTITY = "itemsQuantity";
    private static final String EVENTS_COUNT = "eventsCount";
    private static final String OUT_DIR = "outDir";
    private static final String OUT_FORMAT = "format";

    public AppProperties readProperties() {
        try {
            Properties props = new Properties();
            props.load(getPropsStream());
            return new AppProperties(
                    props.getProperty(CUSTOMER_IDS),
                    props.getProperty(DATE_RANGE),
                    props.getProperty(ITEMS_FILE),
                    props.getProperty(ITEMS_COUNT),
                    props.getProperty(ITEMS_QUANTITY),
                    props.getProperty(EVENTS_COUNT),
                    props.getProperty(OUT_DIR),
                    props.getProperty(OUT_FORMAT)
            );
        } catch (IOException e) {
            log.warn("Exception occurred during parsing properties.", e);
            throw new PropertiesReaderException("Exception occurred during parsing properties." + e.getMessage());
        }
    }

    InputStream getPropsStream() {
        try {
            return new FileInputStream(Paths.get(FILE).toFile());
        } catch (FileNotFoundException e) {
            log.warn("Exception occurred during parsing properties.", e);
            throw new PropertiesReaderException("Exception occurred during parsing properties." + e.getMessage());
        }
    }
}
