package classes.properties;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppPropertiesReaderTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldParseCommandLineArgs() {
        // given
        String args = "itemsFile=/storage/items.csv\n" +
                "customerIds=1:2\n" +
                "dateRange=2018-03-08T00:00:00.000-0100\":\"2018-03-08T23:59:59.999-0100\n" +
                "itemsCount=1:2\n" +
                "itemsQuantity=1:2\n" +
                "outDir=/storage\n" +
                "eventsCount=1\n" +
                "format=xml\n";
        PropertiesReader propertiesReader= mock(PropertiesReader.class);
        when(propertiesReader.readProperties()).thenCallRealMethod();
        try {
            when(propertiesReader.getPropsStream()).thenReturn(IOUtils.toInputStream(args, "UTF-8"));
        } catch (IOException e) {
            assertTrue("Mocking Property stream failed." ,false);
        }

        //when
        AppProperties appProperties = propertiesReader.readProperties();

        //then
        assertEquals("1:2", appProperties.getCustomerIds());
        assertEquals("2018-03-08T00:00:00.000-0100\":\"2018-03-08T23:59:59.999-0100", appProperties.getDateRange());
        assertEquals("/storage/items.csv", appProperties.getItemsFile());
        assertEquals("1:2", appProperties.getItemsCount());
        assertEquals("1:2", appProperties.getItemsQuantity());
        assertEquals("1", appProperties.getEventsCount());
        assertEquals("/storage", appProperties.getOutDir());
        assertEquals("xml", appProperties.getFormat());
    }
}
