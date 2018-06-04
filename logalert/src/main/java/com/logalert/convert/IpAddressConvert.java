package com.logalert.convert;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.logalert.util.LocalIpAddressUtil;

public class IpAddressConvert extends ClassicConverter {

    private static String ip= LocalIpAddressUtil.resolveLocalAddress().getHostAddress();
    @Override
    public String convert(ILoggingEvent event) {
        return ip;
    }
}
