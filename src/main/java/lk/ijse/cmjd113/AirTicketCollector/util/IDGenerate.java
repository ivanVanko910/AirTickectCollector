package lk.ijse.cmjd113.AirTicketCollector.util;

import java.util.UUID;

public class IDGenerate {
    public static String airportId() {
        return "APT-" + UUID.randomUUID();
    }

    public static String userId() {
        return "USR-" + UUID.randomUUID();
    }

    public static String bookingId() {
        return "BKG-" + UUID.randomUUID();
    }

    public static String flightNo() {
        return "FLT-" + UUID.randomUUID();
    }
}
