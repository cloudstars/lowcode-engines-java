package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.pdf.parser.PdfBuildConfigParser;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PdfParserTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    public PdfBuildConfigParser pdfBuildConfigParser() {
        return new PdfBuildConfigParser();
    }
}