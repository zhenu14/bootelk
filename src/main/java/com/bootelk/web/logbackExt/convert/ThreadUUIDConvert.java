package com.bootelk.web.logbackExt.convert;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.bootelk.web.logbackExt.utils.ThreadUUIDContext;

public class ThreadUUIDConvert extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return ThreadUUIDContext.get();
    }
}