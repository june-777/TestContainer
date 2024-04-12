import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

@Testcontainers
@Slf4j
public class ReuseTestContainerTest {

	@Container
	static MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:8"))
			.withReuse(true);

	@Test
	void test1() {
		log.info("instance = {}", mysql);
	}

	@Test
	void test2() {
		log.info("instance = {}", mysql);
	}

}
