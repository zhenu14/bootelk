package com.bootelk.web.logbackExt.convert;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.bootelk.web.logbackExt.utils.LocalIpAddressUtil;

public class IpAddressConvert extends ClassicConverter {

    private static String ip= LocalIpAddressUtil.resolveLocalAddress().getHostAddress();
    @Override
    public String convert(ILoggingEvent event) {
        return ip;
    }
}
