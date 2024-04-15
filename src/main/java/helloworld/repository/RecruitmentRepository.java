package helloworld.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import helloworld.domain.Recruitment;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

}
