package foo.bar;

import org.springframework.stereotype.Component;

@Component
public class Welcome {
    public static String sayHello() {
        return "Hello world!";
    }
}
