package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WelcomeController {

    private String message;

    public WelcomeController(@Value("${welcome.message:Hello World!!!}")String message) {
        this.message = message;
    }

    public String sayHello() {
        return message;
    }
}