package net.cf.commons.test.db.dataset.mongo;

import de.flapdoodle.embed.mongo.transitions.MongodStarter;
import net.cf.commons.test.db.dataset.MongoDataSetOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class CommonsTestMongoConfiguration {

    private static final MongodStarter starter = MongodStarter.withDefaults();

    @Bean
    public MongoDataSetOperator mongoDataSetOperator(MongoTemplate mongoTemplate) {
        return new MongoDataSetOperator(mongoTemplate);
    }

}
