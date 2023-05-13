package com.hotelalura.util;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class DateHotelUtil {
    public static Date dataAgora = Date.from(Instant.now());
    public static ZoneId getZoneId() {
        return ZoneId.of("America/Sao_Paulo");
    }

}
