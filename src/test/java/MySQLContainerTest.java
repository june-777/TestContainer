import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

@Testcontainers
@Slf4j
public class MySQLContainerTest {

	@Container
	static MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:8"))
			.withDatabaseName("ludo-test")
			.withUsername("root")
			.withPassword("1234")
			.withCommand("--collation-server=utf8mb4_unicode_ci",
					"--character-set-server=utf8mb4"
			)
			.withEnv("MYSQL_ROOT_PASSWORD", "root")
			.withEnv("MYSQL_DATABASE", "ludo")
			.withEnv("MYSQL_ALLOW_EMPTY_PASSWORD", "yes");

	@Test
	void test() {
		loggingContainerInfo();

		assertThat(mysql.getDatabaseName()).isEqualTo("ludo-test");
		assertThat(mysql.getUsername()).isEqualTo("root");
		assertThat(mysql.getPassword()).isEqualTo("1234");
		assertThat(mysql.getCommandParts()).hasSize(2);
	}

	void loggingContainerInfo() {
		log.info("===== container basic info =====");
		log.info("컨테이너 이름 = {}", mysql.getContainerName());
		log.info("컨테이너 ID = {}", mysql.getContainerId());
		log.info("컨테이너 정보 = {}", mysql.getContainerInfo());

		log.info("\n===== mysql basic info =====");
		log.info("JDBC URL = {}", mysql.getJdbcUrl());
		log.info("호스트 = {}", mysql.getHost());
		log.info("외부 바인딩 포트 번호 = {}", mysql.getMappedPort(3306));
		log.info("MySQL 데이터베이스 이름 = {}", mysql.getDatabaseName());
		log.info("MySQL 유저 이름 = {}", mysql.getUsername());
		log.info("MySQL 비밀번호 = {}", mysql.getPassword());
	}
}
