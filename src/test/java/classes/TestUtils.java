package classes;

import java.time.LocalTime;
import java.time.ZonedDateTime;

public class TestUtils {

    public ZonedDateTime getBeginOfToday() {
        return ZonedDateTime.now().with(LocalTime.MIN);
    }

    public ZonedDateTime getEndOfToday() {
        return ZonedDateTime.now().with(LocalTime.MAX);
    }
}