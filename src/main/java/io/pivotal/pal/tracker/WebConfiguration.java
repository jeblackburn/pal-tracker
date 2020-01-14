package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@Configuration
public class WebConfiguration {

    @Bean
    public RouterFunction<ServerResponse> sayHello(WelcomeController controller) {
        System.out.println("Wiring sayHello...");
        return route().GET("/", request -> ok().body(controller.sayHello())).build();
    }

    @Bean
    public RouterFunction<ServerResponse> getEnv(EnvController envController) {
        return route().GET("/env",request -> ok().body(envController.getEnv())).build();
    }

}
