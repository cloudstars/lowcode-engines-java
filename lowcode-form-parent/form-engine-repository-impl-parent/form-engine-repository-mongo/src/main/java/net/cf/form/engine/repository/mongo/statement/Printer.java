package net.cf.form.engine.repository.mongo.statement;

public class Printer {

    private VisitValue value;

    public void append(VisitValue val) {
        if (value == null) {
            value = val;
            return;
        } else {
            if (val instanceof ComplexVisitValue) {
                ((CommonVisitValue)val).setValue(value);
            }
        }
    }

    public VisitValue print() {
        VisitValue visitValue =  this.value;
        this.value = null;
        return visitValue;
    }



}
