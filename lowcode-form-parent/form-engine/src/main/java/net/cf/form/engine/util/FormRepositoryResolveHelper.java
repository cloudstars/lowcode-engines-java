package net.cf.form.engine.util;

import net.cf.form.engine.FormRepositoryProvider;
import net.cf.form.engine.object.XObject;
import net.cf.form.engine.oql.ast.statement.OqlInsertStatement;
import net.cf.form.repository.FormRepository;
import org.springframework.stereotype.Component;

@Component
public class FormRepositoryResolveHelper {

    private FormRepositoryProvider resolveRepository;

    public FormRepository resolveRepository(OqlInsertStatement statement) {
        XObject object = statement.getObjectSource().getResolvedObject();
        assert (object != null);
        return resolveRepository.getByObject(object);
    }
}
