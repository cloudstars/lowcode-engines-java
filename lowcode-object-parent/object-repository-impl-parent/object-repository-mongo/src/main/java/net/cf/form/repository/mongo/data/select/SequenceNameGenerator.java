package net.cf.form.repository.mongo.data.select;

/**
 * 自动生成名字
 */
public class SequenceNameGenerator {

    private int index = 1;

    public String getNextName(String prefix) {
        String name = prefix + "@" + index;
        index++;
        return name;
    }

}
