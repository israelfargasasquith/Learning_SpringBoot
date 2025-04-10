package io.raeliss.runnerz;

import java.time.LocalDateTime;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import ch.qos.logback.classic.Logger;
import foo.bar.Welcome;
import io.raeliss.runnerz.run.Location;
import io.raeliss.runnerz.run.Run;



@SpringBootApplication
public class RunnerzApplication {

	private static final Logger log = (Logger) LoggerFactory.getLogger(RunnerzApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);

		// #Second
		log.info("\nApplication running sucsesfully!");
		log.info("\nActived changing!");

		// #First
		// ConfigurableApplicationContext context =
		// SpringApplication.run(RunnerzApplication.class, args);

		// var hi = Welcome.sayHello();

		// System.out.println(hi);

		// Better way is to call the Springboot collection of instances and call for
		// that particular bean using ConfigurableApplicationContext

		// Hello hello = (Hello) context.getBean("hello");

		// System.out.println(hello);

		// Welcome well = (Welcome) context.getBean("welcome");

		// System.out.println(well.sayHelloNormal());

	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Run run = new Run(1, "Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5.0,
					Location.INDOOR);
			System.out.println(run);
		};
	}

}
