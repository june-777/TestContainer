import static org.assertj.core.api.Assertions.*;
import static org.testcontainers.containers.Container.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

@Testcontainers
@Slf4j
public class ExecuteCommandTest {

	@Container
	MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:8"));

	@Test
	@DisplayName("도커 컨테이너 접근 후, 커맨드 실행 - docker exec와 유사함")
	void test() throws IOException, InterruptedException {
		ExecResult execResult = mysql.execInContainer("ls", "-al");
		String stdout = execResult.getStdout();
		log.info("output = {}", stdout);
	}

	@Test
	@DisplayName("Docker environment 설정")
	void docker_environment_test() {
		List<String> beforeEnv = mysql.getEnv();
		log.info("before env = {}", beforeEnv);

		mysql.addEnv("MYSQL_ROOT_PASSWORD", "root");
		mysql.addEnv("MYSQL_DATABASE", "ludo");
		mysql.addEnv("MYSQL_ALLOW_EMPTY_PASSWORD", "yes");

		List<String> afterEnv = mysql.getEnv();
		log.info("before env = {}", afterEnv);
	}

	@Test
	@DisplayName("Docker command 설정")
	void docker_command_test() {
		log.info("Before command parts");
		Arrays.stream(mysql.getCommandParts())
				.forEach(command -> log.info("command = {}", command));
		assertThat(mysql.getCommandParts()).isEmpty();

		mysql.withCommand(
				"--collation-server=utf8mb4_unicode_ci",
				"--character-set-server=utf8mb4"
		);

		log.info("After command parts");
		Arrays.stream(mysql.getCommandParts())
				.forEach(command -> log.info("command = {}", command));
		assertThat(mysql.getCommandParts()).hasSize(2);
	}
}
