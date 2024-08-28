package com.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class DatabaseApplication {


    JenkinsAPI jenkinsAPI;
    {
        try {
            jenkinsAPI = new JenkinsAPI();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {


        ConfigurableApplicationContext ctx = SpringApplication.run(DatabaseApplication.class, args);
        ctx.close();
    }



}
