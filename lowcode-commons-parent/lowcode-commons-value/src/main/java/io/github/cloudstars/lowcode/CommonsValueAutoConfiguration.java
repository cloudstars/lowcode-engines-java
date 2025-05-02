package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.value.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsValueAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueTypeFactory.register(TextValueTypeImpl.class);
        ValueTypeFactory.register(NumberValueTypeImpl.class);
        ValueTypeFactory.register(BooleanValueTypeImpl.class);
        ValueTypeFactory.register(DateValueTypeImpl.class);
        ValueTypeFactory.register(TimeValueTypeImpl.class);
        ValueTypeFactory.register(ObjectValueTypeImpl.class);
        ValueTypeFactory.register(ArrayValueTypeImpl.class);
        ValueTypeFactory.register(OptionValueTypeImpl.class);
    }

}
