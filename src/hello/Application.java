package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// tell that this is a configuration class and it should scan all the beans itself
@Configuration
@ComponentScan
public class Application {

    @Bean
    MessageService creatMessageServiceBean() {
        return new MessageService() {
            @Override
            public String getMessage() {
                return "Hello World!!!";
            }
        };
    }

    public static void main(String[] args) {

        // create the application context with all the beans
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        //extract the component bean from the application context built above
        MessagePrinter printer = context.getBean(MessagePrinter.class);
        printer.printMessage();
    }
}
