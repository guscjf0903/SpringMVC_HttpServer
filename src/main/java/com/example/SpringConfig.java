package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class SpringConfig {
    @Bean
    public Connection SpringConfig() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String username = "guscjf0903";
        String password = "tnthtn35";

        return DriverManager.getConnection(url, username, password);
    }

}
