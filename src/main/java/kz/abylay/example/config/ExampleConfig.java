package kz.abylay.example.config;

import kz.abylay.example.model.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class ExampleConfig {
    @Bean
    @Scope("singleton")
    public Users createAccount() {
        return new Users();
    }

    @Bean
    @Scope("prototype")
    public Users getNewAcc(){
        return new Users();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
