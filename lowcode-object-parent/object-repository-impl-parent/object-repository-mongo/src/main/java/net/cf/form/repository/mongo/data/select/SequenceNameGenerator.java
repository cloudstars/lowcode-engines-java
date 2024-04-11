package net.cf.form.repository.mongo.data.select;

/**
 * 自动生成名字
 */
public class SequenceNameGenerator {

    private int index = 1;

    private String prefix;

    public SequenceNameGenerator(String prefix) {
        this.prefix = prefix;
    }

    public String getNextName() {
        String name = prefix + "@" + index;
        index++;
        return name;
    }

}
