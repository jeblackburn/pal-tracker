package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import javax.sql.DataSource;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@Configuration
public class WebConfiguration {

    @Bean
    public RouterFunction<ServerResponse> webRoutes(WelcomeController controller, EnvController envController) {
        return route(GET("/"), request -> ok().body(controller.sayHello()))
                .andRoute(GET("/env"), request -> ok().body(envController.getEnv()));
    }

//    @Bean
//    public RouterFunction<?> timeEntryRoutes(TimeEntryController timeEntryController) {
//        return route(POST("/time-entries"), request ->  ServerResponse.status(HttpStatus.CREATED).body(timeEntryController.create(request.body(TimeEntry.class))))
//                .andRoute(GET("/time-entries"),
//                        request -> ok().body(timeEntryController.list()));
//    }

    @Bean
    public TimeEntryRepository timeEntryRepository(DataSource dataSource) {
        return new JdbcTimeEntryRepository(dataSource);
    }

}
