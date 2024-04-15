package helloworld.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import helloworld.domain.Recruitment;
import helloworld.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

	private final RecruitmentRepository recruitmentRepository;

	public List<Recruitment> findRecruitments() {
		return recruitmentRepository.findAll();
	}

	public Recruitment findRecruitment(final Long id) {
		Optional<Recruitment> findRecruitment = recruitmentRepository.findById(id);
		return findRecruitment.orElseThrow(() -> new IllegalArgumentException("모집 공고가 없습니다."));
	}

}
