package net.cf.form.engine.repository.mongo.statement;

public class TextVisitValue implements VisitValue{

    private String value;


    public TextVisitValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }
}
