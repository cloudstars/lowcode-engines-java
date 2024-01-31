package net.cf.form.engine.repository.mongo.statement;

public class ComplexVisitValue implements VisitValue {

    private VisitValue value;

    public VisitValue getValue() {
        return value;
    }

    public void setValue(VisitValue value) {
        this.value = value;
    }
}
