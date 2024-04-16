package helloworld.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import helloworld.domain.Recruitment;
import helloworld.repository.RecruitmentRepository;

@SpringBootTest
class RecruitmentServiceTest2 {

	@Autowired
	RecruitmentRepository recruitmentRepository;

	@Autowired
	RecruitmentService recruitmentService;

	@BeforeEach
	void init() {
		List<Recruitment> recruitments = List.of(new Recruitment("모집공고A"), new Recruitment("모집공고B"),
				new Recruitment("모집공고C"), new Recruitment("모집공고D"),
				new Recruitment("모집공고E"));
		recruitmentRepository.saveAll(recruitments);
	}

	@Test
	void findAllTest() {
		List<Recruitment> recruitments = recruitmentService.findRecruitments();
		assertThat(recruitments).hasSize(5);
	}
}