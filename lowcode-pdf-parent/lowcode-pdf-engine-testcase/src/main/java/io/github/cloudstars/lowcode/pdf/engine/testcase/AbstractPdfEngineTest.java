package io.github.cloudstars.lowcode.pdf.engine.testcase;

import io.github.cloudstars.lowcode.commons.lang.exception.SystemException;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;
import io.github.cloudstars.lowcode.pdf.engine.PdfEngine;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public abstract class AbstractPdfEngineTest {

    @Resource
    private PdfEngine pdfEngine;

    protected void build1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("pdf-build1.json");
        PdfBuildConfig config = new PdfBuildConfig(configJson);

        File file = new File("target/pdf-build1.pdf");
        if (file.exists()) {
            file.delete();
        }

        try (FileOutputStream os = new FileOutputStream(file)) {
            this.pdfEngine.build(config, new HashMap<>(), os);
        } catch (IOException e) {
            throw new SystemException("构建EXCEL pdf-build1.pdf 异常", e);
        }
    }

    protected void build2() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("pdf-build2.json");
        PdfBuildConfig config = new PdfBuildConfig(configJson);

        File file = new File("target/pdf-build2.pdf");
        if (file.exists()) {
            file.delete();
        }

        try (FileOutputStream os = new FileOutputStream(file)) {
            this.pdfEngine.build(config, new HashMap<>(), os);
        } catch (IOException e) {
            throw new SystemException("构建EXCEL pdf-build2.pdf 异常", e);
        }
    }

}
