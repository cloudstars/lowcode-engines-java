package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.value.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsValueAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueTypeClassFactory.register(TextValueTypeImpl.class);
        ValueTypeClassFactory.register(NumberValueTypeImpl.class);
        ValueTypeClassFactory.register(BooleanValueTypeImpl.class);
        ValueTypeClassFactory.register(DateValueTypeImpl.class);
        ValueTypeClassFactory.register(TimeValueTypeImpl.class);
        ValueTypeClassFactory.register(ObjectValueTypeImpl.class);
        ValueTypeClassFactory.register(ArrayValueTypeImpl.class);
        ValueTypeClassFactory.register(OptionValueTypeImpl.class);
    }

}
