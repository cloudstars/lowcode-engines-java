package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.pdf.vendor.convertor.ElementConvertorClassFactory;
import io.github.cloudstars.lowcode.pdf.vendor.convertor.TextElementConvertor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class OpenPdfVendorAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ElementConvertorClassFactory.register(TextElementConvertor.class);
    }

}
