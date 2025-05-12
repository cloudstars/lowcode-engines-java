package io.github.cloudstars.lowcode.pdf.engine.openpdf;

import io.github.cloudstars.lowcode.pdf.engine.PdfEngine;
import io.github.cloudstars.lowcode.pdf.engine.PdfEngineImpl;
import io.github.cloudstars.lowcode.pdf.vendor.OpenPdfBuilderFactory;
import io.github.cloudstars.lowcode.pdf.vendor.PdfBuilderFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

public class PdfEngineOpenPdfAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    public PdfBuilderFactory pdfBuilderFactory() {
        return new OpenPdfBuilderFactory();
    }

    @Bean
    public PdfEngine pdfEngine(PdfBuilderFactory pdfBuilderFactory) {
        return new PdfEngineImpl(pdfBuilderFactory);
    }

}
