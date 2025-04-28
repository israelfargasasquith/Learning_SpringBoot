package io.raeliss.runnerz;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import ch.qos.logback.classic.Logger;
import foo.bar.Welcome;
import io.raeliss.runnerz.run.Location;
import io.raeliss.runnerz.run.Run;
import io.raeliss.runnerz.user.User;
import io.raeliss.runnerz.user.UserHTTPClient;
import io.raeliss.runnerz.user.UserRestClient;
import io.raeliss.runnerz.run.JdbcClientRunRepository;

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
	UserHTTPClient userHTTPClient() {
		RestClient restClient = RestClient.builder()
				.baseUrl("https://jsonplaceholder.typicode.com")
				.build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
				.build();
		return factory.createClient(UserHTTPClient.class);
	}

	@Bean
	CommandLineRunner runner(UserRestClient client) {
		return args -> {
			List<User> users = client.getUsers();
			System.out.println("Users: " + users);
		};
	}

}
