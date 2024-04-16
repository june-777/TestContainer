package helloworld.service;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import helper.TestContainerLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySQLTestContainer implements BeforeAllCallback {

	// static := 테스트 컨테이너 테스트 전체 1회만 생성
	private static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8"))
			.withDatabaseName("ludo-test");

	@Override
	public void beforeAll(ExtensionContext extensionContext) throws Exception {
		log.info("before All");
		if (mySQLContainer.isRunning()) {
			return;
		}
		log.info("===== MySQL Container 실행 =====");
		mySQLContainer.start();
		setDatasourceProperties();
		TestContainerLogger.loggingContainerInfo(mySQLContainer);
	}

	private void setDatasourceProperties() {
		System.setProperty("spring.datasource.url", mySQLContainer.getJdbcUrl());
		System.setProperty("spring.datasource.username", mySQLContainer.getUsername());
		System.setProperty("spring.datasource.password", mySQLContainer.getPassword());
	}
}
