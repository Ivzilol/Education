package bg.softuni.intro.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZooConfig {

    @Bean("normalDog")
    public Animal dog(){
        return new Dog();
    }

    @Bean("mySuperDog")
    public Animal superDog(){
        return new Dog(true);
    }

    @Bean("cat")
    public Animal cat(){
        return new Cat();
    }

}
