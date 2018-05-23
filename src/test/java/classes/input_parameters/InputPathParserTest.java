package classes.input_parameters;

import classes.parsers.ParseException;
import org.junit.Test;

import java.nio.file.Path;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputPathParserTest {
    @Test
    public void shouldParseFile() {
        // given
        String pathString = "someDummyValue";
        InputPathParser mock = mock(InputPathParser.class);
        when(mock.validateFile(any(Path.class))).thenReturn(true);
        when(mock.parseFile(any(String.class))).thenCallRealMethod();

        try {
            //when
            Path path = mock.parseFile(pathString);

            //then
            assertTrue("ParseException shouldn't be thrown", true);
        } catch (ParseException e) {
            assertTrue("ParseException shouldn't be thrown", false);
        }
    }

    @Test
    public void shouldParseDirectory() {
        // given
        String pathString = "someDummyValue";
        InputPathParser mock = mock(InputPathParser.class);
        when(mock.validateDirectory(any(Path.class))).thenReturn(true);
        when(mock.parseDirectory(any(String.class))).thenCallRealMethod();

        try {
            //when
            Path path = mock.parseDirectory(pathString);

            //then
            assertTrue("ParseException shouldn't be thrown", true);
        } catch (ParseException e) {
            assertTrue("ParseException shouldn't be thrown", false);
        }
    }

    @Test
    public void shouldThrowExceptionWhileParsingFile() {
        // given
        String pathString = "someDummyValue";
        InputPathParser mock = mock(InputPathParser.class);
        when(mock.validateFile(any(Path.class))).thenReturn(false);
        when(mock.parseFile(any(String.class))).thenCallRealMethod();

        try {
            //when
            Path path = mock.parseFile(pathString);

            //then
            assertTrue("ParseException should be thrown", false);
        } catch (ParseException e) {
            assertTrue("ParseException should be thrown", true);
        }
    }

    @Test
    public void shouldThrowExceptionWhileParsingDirectory() {
        // given
        String pathString = "someDummyValue";
        InputPathParser mock = mock(InputPathParser.class);
        when(mock.validateFile(any(Path.class))).thenReturn(false);
        when(mock.parseDirectory(any(String.class))).thenCallRealMethod();

        try {
            //when
            Path path = mock.parseDirectory(pathString);

            //then
            assertTrue("ParseException should be thrown", false);
        } catch (ParseException e) {
            assertTrue("ParseException should be thrown", true);
        }
    }
}
