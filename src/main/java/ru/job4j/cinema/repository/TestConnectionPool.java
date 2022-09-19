package ru.job4j.cinema.repository;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Properties;

public class TestConnectionPool extends BasicDataSource {
    public TestConnectionPool() {
        var configuration = new Properties();
        try (var in = TestConnectionPool
                .class
                .getClassLoader()
                .getResourceAsStream("h2db.properties")) {
            configuration.load(in);
            setDriverClassName(configuration.getProperty("jdbc.driver"));
            setUsername(configuration.getProperty("jdbc.username"));
            setPassword(configuration.getProperty("jdbc.password"));
            setUrl(configuration.getProperty("jdbc.url"));
            setMinIdle(5);
            setMaxIdle(10);
            setMaxTotal(100);
            setMaxOpenPreparedStatements(100);

        } catch (Exception e) {
            throw new RuntimeException("Can't create connection pool");
        }
    }
}
