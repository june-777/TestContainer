package helloworld.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import helloworld.domain.Recruitment;
import helloworld.repository.RecruitmentRepository;

@SpringBootTest
@ExtendWith(MySQLTestContainer.class)
public class RecruitmentFindServiceTest {

	@Autowired
	RecruitmentRepository recruitmentRepository;

	@Autowired
	RecruitmentService recruitmentService;

	@Test
	@Transactional
	void findTest() {
		Recruitment recruitment = new Recruitment("모집공고");
		recruitmentRepository.save(recruitment);
		Recruitment findRecruitment = recruitmentService.findRecruitment(recruitment.getId());
		assertThat(findRecruitment).isEqualTo(recruitment);
	}

}
