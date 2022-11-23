package hello.formatter;

import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.text.NumberFormat;

import static org.assertj.core.api.Assertions.assertThat;

public class FormattingConversionServiceTes {
    @Test
    void formattingConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        conversionService.addFormatter(new MyNumberFormatter());
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        String convert = conversionService.convert(1000, String.class);
        assertThat(convert).isEqualTo("1,000");


        Number convert2 = conversionService.convert("1,000", Long.class);
        assertThat(convert2).isEqualTo(1000L);

    }
}
