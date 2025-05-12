package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.value.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsValueAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueTypeClassFactory.register(TextValueType.class);
        ValueTypeClassFactory.register(NumberValueType.class);
        ValueTypeClassFactory.register(BooleanValueType.class);
        ValueTypeClassFactory.register(DateValueType.class);
        ValueTypeClassFactory.register(TimeValueType.class);
        ValueTypeClassFactory.register(ObjectValueType.class);
        ValueTypeClassFactory.register(ArrayValueType.class);
        ValueTypeClassFactory.register(OptionValueType.class);
    }

}
