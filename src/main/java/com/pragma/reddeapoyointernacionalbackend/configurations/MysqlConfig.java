package com.pragma.reddeapoyointernacionalbackend.configurations;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Duration;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.*;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
@EnableTransactionManagement
public class MysqlConfig extends AbstractR2dbcConfiguration {

    @Autowired
    Environment environment;

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER,POOLING_DRIVER)
                .option(PROTOCOL,environment.getProperty("spring.r2dbc.protocol"))
                .option(HOST,environment.getProperty("spring.r2dbc.host"))
                .option(USER,environment.getProperty("spring.r2dbc.user"))
                .option(PASSWORD,environment.getProperty("spring.r2dbc.pswd"))
                .option(DATABASE,environment.getProperty("spring.r2dbc.schema"))
                .option(INITIAL_SIZE,Integer.parseInt(environment.getProperty("spring.r2dbc.pool.initial")))
                .option(MAX_SIZE,Integer.parseInt(environment.getProperty("spring.r2dbc.pool.max")))
                .build());

        ConnectionPoolConfiguration configuration = ConnectionPoolConfiguration.builder()
                .maxCreateConnectionTime(Duration.ofSeconds(10))
                .maxIdleTime(Duration.ofMinutes(10))
                .connectionFactory(connectionFactory)
                .build();
        return new ConnectionPool(configuration);
    }

    @Bean
    public ReactiveTransactionManager getTransactionMgr(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

}
