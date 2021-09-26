package ru.goryachev.multichief.mrp.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.goryachev.multichief.mrp")
@EntityScan(basePackages = "ru.goryachev.multichief.mrp")
public class AppInit extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder appBuilder) {
        return super.configure(appBuilder);
    }
}
