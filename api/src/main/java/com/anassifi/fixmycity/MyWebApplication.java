package com.anassifi.fixmycity;

import com.anassifi.fixmycity.Seeders.*;
import com.anassifi.fixmycity.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyWebApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MyWebApplication.class, args);
        // Seeders
        UserService userService = context.getBean(UserService.class);
        if (userService.getAll().size() == 0) {
            context.getBean(RoleSeeder.class).Seed();
            context.getBean(SpecialisationSeeder.class).Seed(30);
            context.getBean(AdminSeeder.class).Seed();
            context.getBean(AdministrationSeeder.class).Seed(30);
            context.getBean(ProblemSeeder.class).Seed(30);
            context.getBean(ReportSeeder.class).Seed(30);
        }

    }
}