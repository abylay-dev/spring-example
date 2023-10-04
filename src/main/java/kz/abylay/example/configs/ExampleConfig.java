package kz.abylay.example.configs;

import kz.abylay.example.models.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ExampleConfig {
    @Bean
    @Scope("singleton")
    public Person createAccount() {
        return new Person();
    }

    @Bean
    @Scope("prototype")
    public Person getNewAcc() {
        return new Person();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
