package helloworld.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import helloworld.domain.Recruitment;
import helloworld.repository.RecruitmentRepository;

@SpringBootTest
class RecruitmentServiceTest {

	@Autowired
	RecruitmentRepository recruitmentRepository;

	@Autowired
	RecruitmentService recruitmentService;

	@Test
	@Transactional
	void findAllTest() {
		recruitmentRepository.saveAll(List.of(new Recruitment("모집공고A"), new Recruitment("모집공고B"),
				new Recruitment("모집공고C"), new Recruitment("모집공고D"),
				new Recruitment("모집공고E")));
		List<Recruitment> recruitments = recruitmentService.findRecruitments();
		assertThat(recruitments).hasSize(5);
	}
}