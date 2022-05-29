package benchmark.mongo.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:mongo.properties")
@Configuration
public class MongoAutoConfiguration {}
