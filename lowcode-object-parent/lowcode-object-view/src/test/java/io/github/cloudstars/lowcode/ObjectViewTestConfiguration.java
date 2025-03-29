package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.object.view.form.ObjectFormView;
import io.github.cloudstars.lowcode.object.view.form.ObjectFormViewImpl;
import io.github.cloudstars.lowcode.object.view.table.TableView;
import io.github.cloudstars.lowcode.object.view.table.TableViewConfigParser;
import io.github.cloudstars.lowcode.object.view.table.TableViewImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectViewTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    public TableViewConfigParser tableViewConfigParser() {
        return new TableViewConfigParser();
    }

    @Bean
    public ObjectFormView objectFormView() {
        return new ObjectFormViewImpl();
    }

    @Bean
    public TableView objectTableView() {
        return new TableViewImpl();
    }

}