package io.github.cloudstars.lowcode.pdf.engine.openpdf;

import io.github.cloudstars.lowcode.OpenPdfEngineTestApplication;
import io.github.cloudstars.lowcode.pdf.engine.testcase.AbstractPdfEngineTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OpenPdfEngineTestApplication.class)
public class PdfEngineTest extends AbstractPdfEngineTest {

    @Test
    @Override
    public void build1() {
        super.build1();
    }

    @Test
    @Override
    public void build2() {
        super.build2();
    }

}
