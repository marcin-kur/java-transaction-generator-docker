package classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class TestUtils {

    public ZonedDateTime getBeginOfToday() {
        return ZonedDateTime.of(LocalDate.now(ZoneId.systemDefault()), LocalTime.MIDNIGHT, ZoneId.systemDefault());
    }

    public ZonedDateTime getEndOfToday() {
        return getBeginOfToday().plusDays(1).minus(1, ChronoUnit.MILLIS);
    }
}