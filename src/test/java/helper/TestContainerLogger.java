package helper;

import org.testcontainers.containers.MySQLContainer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestContainerLogger {

	public static void loggingContainerInfo(MySQLContainer<?> container) {
		log.info("===== container basic info =====");
		log.info("컨테이너 이름 = {}", container.getContainerName());
		log.info("컨테이너 ID = {}", container.getContainerId());
		log.info("컨테이너 정보 = {}", container.getContainerInfo());

		log.info("\n===== mysql basic info =====");
		log.info("Driver = {}", container.getDriverClassName());
		log.info("JDBC URL = {}", container.getJdbcUrl());
		log.info("호스트 = {}", container.getHost());
		log.info("외부 바인딩 포트 번호 = {}", container.getMappedPort(3306));
		log.info("MySQL 데이터베이스 이름 = {}", container.getDatabaseName());
		log.info("MySQL 유저 이름 = {}", container.getUsername());
		log.info("MySQL 비밀번호 = {}", container.getPassword());
	}
}
