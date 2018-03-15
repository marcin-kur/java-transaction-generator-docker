package classes;

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
            assertTrue("InputParseException shouldn't be thrown", true);
        } catch (InputParseException e) {
            assertTrue("InputParseException shouldn't be thrown", false);
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
            assertTrue("InputParseException shouldn't be thrown", true);
        } catch (InputParseException e) {
            assertTrue("InputParseException shouldn't be thrown", false);
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
            assertTrue("InputParseException should be thrown", false);
        } catch (InputParseException e) {
            assertTrue("InputParseException should be thrown", true);
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
            assertTrue("InputParseException should be thrown", false);
        } catch (InputParseException e) {
            assertTrue("InputParseException should be thrown", true);
        }
    }
}
