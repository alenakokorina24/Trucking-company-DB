package ru.nsu.truckcomp.usermanagement.acl.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.nsu.truckcomp.repository")
@PropertySource("classpath:com.baeldung.acl.datasource.properties")
@EntityScan(basePackages = { "ru.nsu.truckcomp.model" })
public class PersistenceConfig {

}
