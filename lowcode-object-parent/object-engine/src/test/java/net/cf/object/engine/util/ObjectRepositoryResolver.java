package net.cf.object.engine.util;

import net.cf.object.engine.ObjectRepositoryProvider;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.form.repository.ObjectRepository;
import org.springframework.stereotype.Component;

@Component
public class ObjectRepositoryResolver {

    private ObjectRepositoryProvider resolveRepository;

    public ObjectRepository resolveRepository(OqlInsertStatement statement) {
        XObject object = statement.getObjectSource().getResolvedObject();
        assert (object != null);
        return resolveRepository.getByObject(object);
    }
}
