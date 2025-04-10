package io.raeliss.runnerz;

import org.springframework.stereotype.Component;

@Component
public class Hello {
    public String sayHello() {
        return "Hello Spring!";
    }
}
