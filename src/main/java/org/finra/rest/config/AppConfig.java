package org.finra.rest.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AppConfig extends SpringBootServletInitializer {
	private static Class<AppConfig> appConfigClass = AppConfig.class;
	
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(appConfigClass);
    }
}
