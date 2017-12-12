package banoun.aneece;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ObjectOrientedDemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ObjectOrientedDemoApplication.class, args);
		ContainerSearch.main(null);
	}
}
