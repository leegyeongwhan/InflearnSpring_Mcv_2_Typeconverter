package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;


public class ConversionServiceTest {

    @Test
    void conversionService() {
        //등록
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());

        //사용
        Integer convert = conversionService.convert("10", Integer.class);
        assertThat(convert).isEqualTo(10);

        String convert1 = conversionService.convert(10, String.class);
        assertThat(convert1).isEqualTo("10");

        IpPort convert2 = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(convert2).isEqualTo(new IpPort("127.0.0.1", 8080));

        String convert3 = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(convert3).isEqualTo("127.0.0.1:8080");

    }
}
