package net.cf.object.engine.oql.method;

import java.util.List;

/**
 * 方法描述
 */
public class OqlMethodDescriptor {

    private String name;

    private String description;

    private List<Argument> arguments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
    }

    public static class Argument {
        public String name;

        public String description;

        /**
         * 是否可选的
         */
        public boolean optional;
    }
}
