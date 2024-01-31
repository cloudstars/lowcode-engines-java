package net.cf.form.engine.repository.mongo.statement;

public class MethodVisitValue extends ComplexVisitValue{

    private VisitValue value;

    private String method;



    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
